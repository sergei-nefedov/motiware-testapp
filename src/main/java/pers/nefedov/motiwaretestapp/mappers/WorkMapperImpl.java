package pers.nefedov.motiwaretestapp.mappers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pers.nefedov.motiwaretestapp.dto.ProjectDto;
import pers.nefedov.motiwaretestapp.dto.WorkCreationDto;
import pers.nefedov.motiwaretestapp.dto.WorkDto;
import pers.nefedov.motiwaretestapp.exceptions.IncorrectRequestException;
import pers.nefedov.motiwaretestapp.models.Project;
import pers.nefedov.motiwaretestapp.models.Work;
import pers.nefedov.motiwaretestapp.services.ProjectService;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class WorkMapperImpl implements WorkMapper {
    private final ProjectService projectService;
    private final DateMapper dateMapper;
    private final ProjectMapper projectMapper;

    @Override
    public Work mapToWork(WorkCreationDto workCreationDto) {
        Project project = projectService.getProjectById(workCreationDto.getProjectId());
        if (project == null) throw new IncorrectRequestException();
        Work work = new Work();
        work.setName(workCreationDto.getName());
        work.setStartDate(dateMapper.mapToDate(workCreationDto.getStartDate()));
        work.setFinishDate(dateMapper.mapToDate(workCreationDto.getFinishDate()));
        work.setAverageCompletionPercentage(0);
        work.setImplementer(workCreationDto.getImplementer());
        work.setProject(project);
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
        return workDto;
    }

    @Override
    public List<WorkDto> mapToWorkDtoList(List<Work> works) {
        List<WorkDto> workDtoList = new ArrayList<>(works.size());
        for (Work work : works) workDtoList.add(this.mapToWorkDto(work));
        return workDtoList;
    }
}
