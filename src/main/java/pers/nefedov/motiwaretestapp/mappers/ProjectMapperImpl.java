package pers.nefedov.motiwaretestapp.mappers;

import org.springframework.stereotype.Component;
import pers.nefedov.motiwaretestapp.dto.ProjectCreationDto;
import pers.nefedov.motiwaretestapp.dto.ProjectDto;
import pers.nefedov.motiwaretestapp.models.Condition;
import pers.nefedov.motiwaretestapp.models.Project;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProjectMapperImpl implements ProjectMapper {
    @Override
    public Project mapToProject(ProjectCreationDto projectCreationDto) {
        Project project = new Project();
        project.setName(projectCreationDto.getName());
        project.setNumber(projectCreationDto.getNumber());
        project.setDirector(projectCreationDto.getDirector());
        project.setCondition(Condition.FORMING);
        project.setAverageCompletionPercentage(0);
        return project;
    }

    @Override
    public ProjectDto mapToProjectDto(Project project) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setName(project.getName());
        projectDto.setNumber(project.getNumber());
        projectDto.setCondition(project.getCondition().toString());
        projectDto.setAverageCompletionPercentage(project.getAverageCompletionPercentage());
        projectDto.setDirector(project.getDirector());
        return projectDto;
    }

    @Override
    public List<ProjectDto> mapToProjectDtoList(List<Project> projects) {
        List<ProjectDto> projectDtoList = new ArrayList<>(projects.size());
        for (Project project : projects) projectDtoList.add(this.mapToProjectDto(project));
        return projectDtoList;
    }
}
