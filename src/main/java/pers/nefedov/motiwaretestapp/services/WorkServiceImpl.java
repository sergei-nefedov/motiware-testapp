package pers.nefedov.motiwaretestapp.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pers.nefedov.motiwaretestapp.dto.WorkCreationDto;
import pers.nefedov.motiwaretestapp.dto.WorkDto;
import pers.nefedov.motiwaretestapp.dto.WorkPatchDto;
import pers.nefedov.motiwaretestapp.exceptions.ForbiddenException;
import pers.nefedov.motiwaretestapp.exceptions.IncorrectRequestException;
import pers.nefedov.motiwaretestapp.mappers.DateMapper;
import pers.nefedov.motiwaretestapp.mappers.WorkMapper;
import pers.nefedov.motiwaretestapp.models.Checkpoint;
import pers.nefedov.motiwaretestapp.models.Project;
import pers.nefedov.motiwaretestapp.models.Work;
import pers.nefedov.motiwaretestapp.repositories.WorkRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WorkServiceImpl implements WorkService {
    private final WorkRepository workRepository;
    private final WorkMapper workMapper;
    private final DateMapper dateMapper;
    private final ProjectService projectService;
    private final CheckpointService checkpointService;

    @Override
    public WorkDto createWork(WorkCreationDto workCreationDto) {
        Work work = workRepository.save(workMapper.mapToWork(workCreationDto));
        WorkDto workDto = workMapper.mapToWorkDto(work);
        return workDto;
        //return workMapper.mapToWorkDto(workRepository.save(workMapper.mapToWork(workCreationDto)));
    }

    @Override
    public void deleteWork(long id) {
        Work work = getWorkById(id);
        if (work == null) throw new IncorrectRequestException();
        workRepository.deleteById(id);
    }

    @Override
    public WorkDto patchWork(WorkPatchDto workPatchDto) {
        Work work = getWorkById(workPatchDto.getId());
        if (work == null) throw new IncorrectRequestException();
        if (work.getAverageCompletionPercentage() == 100) throw new ForbiddenException();
        String name = workPatchDto.getName();
        Date startDate = dateMapper.mapToDate(workPatchDto.getStartDate());
        Date finishtDate = dateMapper.mapToDate(workPatchDto.getFinishDate());
        double averageCompletionPercentage = workPatchDto.getAverageCompletionPercentage();
        String implementer = workPatchDto.getImplementer();
        long projectId = workPatchDto.getProjectId();
        long checkpointId = workPatchDto.getCheckpointId();

        Project project = projectService.getProjectById(projectId);
        if (projectId != 0 && project == null) throw new IncorrectRequestException();
        if (name != null) work.setName(name);
        if (startDate != null) work.setStartDate(startDate);
        if (finishtDate != null) work.setFinishDate(finishtDate);
        if (implementer != null) work.setImplementer(implementer);
        if (averageCompletionPercentage != 0) work.setAverageCompletionPercentage(averageCompletionPercentage);
        if (projectId != 0) work.setProject(project);
        Checkpoint checkpoint = checkpointService.getCheckpointById(checkpointId);
        if (checkpointId != 0 && checkpoint == null) throw new IncorrectRequestException();
        work.setCheckpoint(checkpoint);

        return workMapper.mapToWorkDto(workRepository.save(work));
    }

    @Override
    public List<WorkDto> getAllByProjectId(long projectId) {
        if (!projectService.existsById(projectId)) throw new IncorrectRequestException();
        return workMapper.mapToWorkDtoList(workRepository.findByProject_Id(projectId));
    }

    @Override
    public List<WorkDto> getAllByCheckpointId(long checkpointId) {
        return null;
    }

    private Work getWorkById(long id) {
        Optional<Work> optionalWork = workRepository.findById(id);
        return optionalWork.orElse(null);
    }

}
