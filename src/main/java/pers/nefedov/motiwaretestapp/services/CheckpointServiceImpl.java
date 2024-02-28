package pers.nefedov.motiwaretestapp.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.nefedov.motiwaretestapp.dto.CheckpointCreationDto;
import pers.nefedov.motiwaretestapp.dto.CheckpointDto;
import pers.nefedov.motiwaretestapp.dto.LoadCheckpointsFromFileDto;
import pers.nefedov.motiwaretestapp.dto.WorkDto;
import pers.nefedov.motiwaretestapp.exceptions.DataLoadException;
import pers.nefedov.motiwaretestapp.exceptions.IncorrectRequestException;
import pers.nefedov.motiwaretestapp.exceptions.NamesConflictException;
import pers.nefedov.motiwaretestapp.mappers.CheckpointMapper;
import pers.nefedov.motiwaretestapp.models.Checkpoint;
import pers.nefedov.motiwaretestapp.models.Project;
import pers.nefedov.motiwaretestapp.repositories.CheckpointRepository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CheckpointServiceImpl implements CheckpointService {
    private final ProjectService projectService;
    private final CheckpointRepository checkpointRepository;
    private final CheckpointMapper checkpointMapper;

    @Override
    public List<CheckpointDto> getAllCheckpoints() {
        return checkpointMapper.mapToCheckpointDtoList(checkpointRepository.findAll());
    }

    @Override
    public List<WorkDto> getWorksByCheckpoint(long checkpoint) {
        return null;
    }

    @Override
    public void loadFromFile(LoadCheckpointsFromFileDto loadCheckpointsFromFileDto) {
        Project project = projectService.getProjectById(loadCheckpointsFromFileDto.getProjectId());
        if (project == null) throw new IncorrectRequestException();
        List<CheckpointCreationDto> checkpointCreationDtoList = new ArrayList<>();
        File file = new File(loadCheckpointsFromFileDto.getFilePath());
        List<String> names = new ArrayList<>();
        try (FileReader fileReader = new FileReader(file)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] stringData = line.split(";");
                if (stringData[0].equalsIgnoreCase("Название")) {
                    line = bufferedReader.readLine();
                    continue;
                }
                CheckpointCreationDto checkpointCreationDto = new CheckpointCreationDto();
                String name = line.split(";")[0];
                if (names.contains(name.toLowerCase())) throw new NamesConflictException();
                names.add(name.toLowerCase());
                checkpointCreationDto.setName(name);
                checkpointCreationDto.setFinishDate(line.split(";")[1]);
                checkpointCreationDto.setProject(project);
                checkpointCreationDtoList.add(checkpointCreationDto);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new DataLoadException();
        }
        overwriteCheckpointsForProject(project, checkpointCreationDtoList);
    }

    @Transactional
    private void overwriteCheckpointsForProject(Project project, List<CheckpointCreationDto> checkpointCreationDtoList) {
        deleteCheckpointsByProject(project);
        saveCheckpointsForProject(checkpointCreationDtoList);
    }

    private void deleteCheckpointsByProject(Project project) {
        checkpointRepository.deleteByProject(project);
    }

    private void saveCheckpointsForProject(List<CheckpointCreationDto> checkpointCreationDtoList) {

        for (CheckpointCreationDto checkpointCreationDto : checkpointCreationDtoList)
            saveCheckpoint(checkpointCreationDto);
    }

    @Override
    public List<CheckpointDto> getCheckpointsByProjectId(long projectId) {
        return checkpointMapper.mapToCheckpointDtoList(checkpointRepository.findByProject_Id(projectId));
    }

    @Override
    public Checkpoint getCheckpointById(long checkpointId) {
        Optional<Checkpoint> optionalCheckpoint = checkpointRepository.findById(checkpointId);
        return optionalCheckpoint.orElse(null);
    }

    public Checkpoint saveCheckpoint(CheckpointCreationDto checkpointCreationDto) {
        return checkpointRepository.save(checkpointMapper.mapToCheckpoint(checkpointCreationDto));
    }
}
