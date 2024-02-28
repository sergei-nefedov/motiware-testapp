package pers.nefedov.motiwaretestapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pers.nefedov.motiwaretestapp.dto.CheckpointDto;
import pers.nefedov.motiwaretestapp.dto.LoadCheckpointsFromFileDto;
import pers.nefedov.motiwaretestapp.services.CheckpointService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/checkpoint")
@Tag(name="Контрольные точки", description="Управление контрольными точками")
public class CheckpointController {
    private final CheckpointService checkpointService;
    @Operation(
            summary = "Загрузка из файла контрольных точек по заданному проекту",
            description = "Проект определяется по его идентификатору, передаваемому в теле запроса. В случае если проекта " +
                    "с указанным идентификатором не существует, а также если в файле содержатся контрольные точки с " +
                    "совпадающими (независимо от регистра) названиями, загрузка не будет произведена."
    )
    @PostMapping("/load_from_file")
    public void loadFromFile(@RequestBody @Valid LoadCheckpointsFromFileDto loadCheckpointsFromFileDto) {
        checkpointService.loadFromFile(loadCheckpointsFromFileDto);
    }

    @Operation(
            summary = "Получение списка всех контрольных точек",
            description = "Позволяет получить все контрольные точки по всем проектам."
    )
    @GetMapping("/all")
    public List<CheckpointDto> getAll() {
        return checkpointService.getAllCheckpoints();
    }

    @Operation(
            summary = "Получение списка контрольных точек по заданному проекту",
            description = "Проект определяется по его идентификатору, передаваемому в пути запроса. В теле запроса передается " +
                    "также путь к файлу, откуда будет производиться загрузка E:/projects/Motiware/motiware-testapp/checkpoints.csv"
    )
    @GetMapping("/{projectId}")
    public List<CheckpointDto> getCheckpointsByProjectId(@PathVariable @Schema(description = "Идентификатор проекта", example = "1") long projectId) {
        return checkpointService.getCheckpointsByProjectId(projectId);
    }
}
