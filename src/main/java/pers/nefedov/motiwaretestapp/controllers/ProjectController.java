package pers.nefedov.motiwaretestapp.controllers;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pers.nefedov.motiwaretestapp.dto.ProjectCreationDto;
import pers.nefedov.motiwaretestapp.dto.ProjectDto;
import pers.nefedov.motiwaretestapp.dto.ProjectPatchDto;
import pers.nefedov.motiwaretestapp.services.ProjectService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/project")
@Tag(name = "Проекты", description = "Управление проектами")
public class ProjectController {
    private ProjectService projectService;

    @Operation(
            summary = "Создание проекта",
            description = "При создании задаются название проекта, его номер, и данные о руководителе проекта. " +
                    "Остальные параметры устанавливаются по умолчанию (состояние - FORMING (оформление), процент выполнения - 0)"
    )
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectDto createProject(@RequestBody @Valid ProjectCreationDto projectCreationDto) {
        return projectService.createProject(projectCreationDto);
    }

    @Operation(
            summary = "Получение всех проектов с заданным состоянием",
            description = "Значения состояния ({condition}) в запросе могут быть FORMING (оформление), APPROVING (согласование), " +
                    "REFINEMENT (доработка), IMPLEMENTATION (исполнение), FINISHED (завершен), регистр не учитывается."
    )
    @GetMapping("/get_by_condition/{condition}")
    public List<ProjectDto> getAllProjectsByCondition(@PathVariable @Schema(description = "Состояние проектов", example = "forming") String condition) {
        return projectService.getAllProjectsByCondition(condition.toUpperCase());
    }

    @Operation(
            summary = "Направление проекта на согласование",
            description = "Результатом операции является изменени состояния проекта на APPROVING (согласование). Может " +
                    "быть выполена только для проектов в состоянии FORMING (оформление) или REFINEMENT (доработка)."
    )
    @PatchMapping("/to_approving")
    public ProjectDto setConditionToApproving(@RequestParam @Valid @Schema(description = "Идентификатор проекта", example = "1") long projectId) {
        return projectService.setConditionToApproving(projectId);
    }
    @Operation(
            summary = "Согласование проекта",
            description = "Результатом операции является изменени состояния проекта на IMPLEMENTATION (исполнение). Может " +
                    "быть выполена только для проектов в состоянии APPROVING (согласование)."
    )
    @PatchMapping("/to_implementation")
    public ProjectDto setConditionToImplementation(@RequestParam @Valid @Schema(description = "Идентификатор проекта", example = "1") long projectId) {
        return projectService.setConditionToImplementation(projectId);
    }
    @Operation(
            summary = "Направление проекта на доработку",
            description = "Результатом операции является изменение состояния проекта на REFINEMENT (доработка). Может " +
                    "быть выполена только для проектов в состоянии APPROVING (согласование)."
    )
    @PatchMapping("/to_refinement")
    public ProjectDto setConditionToRefinement(@RequestParam @Valid @Schema(description = "Идентификатор проекта", example = "1") long projectId) {
        return projectService.setConditionToRefinement(projectId);
    }
    @Operation(
            summary = "Изменение проекта",
            description = "Можно изменить название проекта, его номер, и данные о руководителе проекта. Остальные параметры " +
                    "изменяются по-другому (состояние - через специальные запросы, процент выполнения - автоматически)"
    )
    @PatchMapping("/patch")
    public ProjectDto patchProject(@RequestBody @Valid ProjectPatchDto projectPatchDto) {
        return projectService.patchProject(projectPatchDto);
    }

    @Hidden
    @GetMapping("/id/{id}")
    public ProjectDto getProjectsById(@PathVariable long id) {
        return projectService.getProjectDtoById(id);
    }
}
