package pers.nefedov.motiwaretestapp.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pers.nefedov.motiwaretestapp.dto.ProjectCreationDto;
import pers.nefedov.motiwaretestapp.dto.ProjectDto;
import pers.nefedov.motiwaretestapp.dto.ProjectPatchDto;
import pers.nefedov.motiwaretestapp.exceptions.ForbiddenException;
import pers.nefedov.motiwaretestapp.exceptions.IncorrectRequestException;
import pers.nefedov.motiwaretestapp.mappers.ConditionMapper;
import pers.nefedov.motiwaretestapp.mappers.ProjectMapper;
import pers.nefedov.motiwaretestapp.models.Condition;
import pers.nefedov.motiwaretestapp.models.Project;
import pers.nefedov.motiwaretestapp.models.Work;
import pers.nefedov.motiwaretestapp.repositories.ProjectRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final ConditionMapper conditionMapper;

    @Override
    public ProjectDto createProject(ProjectCreationDto projectCreationDto) {
        Project project = projectMapper.mapToProject(projectCreationDto);
        return projectMapper.mapToProjectDto(projectRepository.save(project));
    }

    @Override
    public List<ProjectDto> getAllProjectsByCondition(String conditionString) {
        if (!Condition.isInEnum(conditionString)) throw new IncorrectRequestException();
//        Condition condition = conditionMapper.mapToCondition(conditionString);
//        List<Project> projectList = projectRepository.findByCondition(condition);
//        return projectMapper.mapToProjectDtoList(projectList);
        return projectMapper.mapToProjectDtoList(projectRepository.findByCondition(conditionMapper.mapToCondition(conditionString)));
    }

    @Override
    public List<Work> gerWorks(Project project) {
        return null;
    }

    @Override
    public Project loadCheckpoints(String filename) {
        return null;
    }

    @Override
    public ProjectDto getProjectDtoById(long id) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        return optionalProject.map(projectMapper::mapToProjectDto).orElse(null);
    }

    @Override
    public Project getProjectById(long id) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        return optionalProject.orElse(null);
    }

    @Override
    public ProjectDto setConditionToApproving(long id) {
        Project project = getProjectById(id);
        if (project == null) throw new IncorrectRequestException();
        if (project.getCondition() != Condition.FORMING && project.getCondition() != Condition.REFINEMENT)
            throw new ForbiddenException();
        projectRepository.updateConditionToApprovingById(id);
        return getProjectDtoById(id);
    }

    @Override
    public ProjectDto setConditionToImplementation(long id) {
        Project project = getProjectById(id);
        if (project == null) throw new IncorrectRequestException();
        if (project.getCondition() != Condition.APPROVING) throw new ForbiddenException();
        projectRepository.updateConditionToImplementationById(id);
        return getProjectDtoById(id);
    }

    @Override
    public ProjectDto setConditionToRefinement(long id) {
        Project project = getProjectById(id);
        if (project == null) throw new IncorrectRequestException();
        if (project.getCondition() != Condition.APPROVING) throw new ForbiddenException();
        projectRepository.updateConditionToRefinementById(id);
        return getProjectDtoById(id);
    }

    @Override
    public ProjectDto patchProject(ProjectPatchDto projectPatchDto) {
        Project project = getProjectById(projectPatchDto.getId());
        if (project == null) throw new IncorrectRequestException();
        String name = projectPatchDto.getName();
        String number = projectPatchDto.getNumber();
        String director = projectPatchDto.getDirector();
        if (name != null) project.setName(name);
        if (number != null) project.setNumber(number);
        if (director != null) project.setDirector(director);
        return projectMapper.mapToProjectDto(projectRepository.save(project));
    }

    @Override
    public boolean existsById(long id) {
        return projectRepository.existsById(id);
    }
}
