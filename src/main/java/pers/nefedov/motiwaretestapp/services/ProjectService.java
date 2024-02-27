package pers.nefedov.motiwaretestapp.services;

import pers.nefedov.motiwaretestapp.dto.ProjectCreationDto;
import pers.nefedov.motiwaretestapp.dto.ProjectDto;
import pers.nefedov.motiwaretestapp.dto.ProjectPatchDto;
import pers.nefedov.motiwaretestapp.models.Project;
import pers.nefedov.motiwaretestapp.models.Work;

import java.util.List;


public interface ProjectService {
    ProjectDto createProject(ProjectCreationDto projectCreationDto);

    List<ProjectDto> getAllProjectsByCondition(String condition);

    List<Work> gerWorks(Project project);

    Project loadCheckpoints(String filename);

    ProjectDto getProjectDtoById(long id);

    Project getProjectById(long id);

    ProjectDto setConditionToApproving(long id);

    ProjectDto setConditionToImplementation(long id);

    ProjectDto setConditionToRefinement(long id);

    ProjectDto patchProject(ProjectPatchDto projectPatchDto);

    boolean existsById(long id);
}


