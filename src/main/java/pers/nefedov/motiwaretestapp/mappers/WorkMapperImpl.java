package pers.nefedov.motiwaretestapp.mappers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pers.nefedov.motiwaretestapp.dto.ProjectDto;
import pers.nefedov.motiwaretestapp.dto.WorkCreationDto;
import pers.nefedov.motiwaretestapp.dto.WorkDto;
import pers.nefedov.motiwaretestapp.exceptions.IncorrectRequestException;
import pers.nefedov.motiwaretestapp.models.Checkpoint;
import pers.nefedov.motiwaretestapp.models.Project;
import pers.nefedov.motiwaretestapp.models.Work;
import pers.nefedov.motiwaretestapp.services.CheckpointService;
import pers.nefedov.motiwaretestapp.services.ProjectService;
import pers.nefedov.motiwaretestapp.validators.DatesValidator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Component
public class WorkMapperImpl implements WorkMapper {
    private final ProjectService projectService;
    private final CheckpointService checkpointService;
    private final DateMapper dateMapper;
    private final ProjectMapper projectMapper;
    private final CheckpointMapper checkpointMapper;
    private final DatesValidator datesValidator;

    @Override
    public Work mapToWork(WorkCreationDto workCreationDto) {
        Project project = projectService.getProjectById(workCreationDto.getProjectId());
        if (project == null) throw new IncorrectRequestException();
        long checkpointId = workCreationDto.getCheckpointId();
        Checkpoint checkpoint = checkpointService.getCheckpointById(checkpointId);
        if (checkpointId != 0 && checkpoint == null) throw new IncorrectRequestException();
        Work work = new Work();
        work.setName(workCreationDto.getName());
        Date startDate = dateMapper.mapToDate(workCreationDto.getStartDate());
        Date finishDate = dateMapper.mapToDate(workCreationDto.getFinishDate());
        Date checkpointDate = checkpoint == null ? null : checkpoint.getFinishDate();
        if (!datesValidator.datesIsCorrect(startDate, finishDate)) throw new IncorrectRequestException();
        if (checkpointDate != null && !datesValidator.datesIsCorrect(finishDate, checkpointDate)) throw new IncorrectRequestException();
        work.setStartDate(dateMapper.mapToDate(workCreationDto.getStartDate()));
        work.setFinishDate(dateMapper.mapToDate(workCreationDto.getFinishDate()));
        work.setAverageCompletionPercentage(0);
        work.setImplementer(workCreationDto.getImplementer());
        work.setProject(project);
        work.setCheckpoint(checkpoint);
        return work;
    }

    @Override
    public WorkDto mapToWorkDto(Work work) {
        WorkDto workDto = new WorkDto();
        workDto.setId(work.getId());
        workDto.setName(work.getName());
        workDto.setStartDate(work.getStartDate());
        workDto.setFinishDate(work.getFinishDate());
        workDto.setAverageCompletionPercentage(work.getAverageCompletionPercentage());
        workDto.setImplementer(work.getImplementer());
        workDto.setProjectDto(projectMapper.mapToProjectDto(work.getProject()));
        workDto.setCheckpointDto(checkpointMapper.mapToCheckpointDto(work.getCheckpoint()));
        return workDto;
    }

    @Override
    public List<WorkDto> mapToWorkDtoList(List<Work> works) {
        List<WorkDto> workDtoList = new ArrayList<>(works.size());
        for (Work work : works) workDtoList.add(this.mapToWorkDto(work));
        return workDtoList;
    }
}
