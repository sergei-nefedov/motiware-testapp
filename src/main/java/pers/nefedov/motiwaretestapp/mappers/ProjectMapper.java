package pers.nefedov.motiwaretestapp.mappers;

import pers.nefedov.motiwaretestapp.dto.ProjectCreationDto;
import pers.nefedov.motiwaretestapp.dto.ProjectDto;
import pers.nefedov.motiwaretestapp.models.Project;

import java.util.List;

public interface ProjectMapper {
   Project mapToProject(ProjectCreationDto projectCreationDto);
   ProjectDto mapToProjectDto(Project project);
   List<ProjectDto> mapToProjectDtoList(List<Project> projects);
}
