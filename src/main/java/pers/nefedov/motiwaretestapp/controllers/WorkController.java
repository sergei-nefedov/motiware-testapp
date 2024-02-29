package pers.nefedov.motiwaretestapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pers.nefedov.motiwaretestapp.dto.WorkCreationDto;
import pers.nefedov.motiwaretestapp.dto.WorkDto;
import pers.nefedov.motiwaretestapp.dto.WorkPatchDto;
import pers.nefedov.motiwaretestapp.services.WorkService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/work")
@Tag(name="Работы", description="Управление работами")
public class WorkController {
    private WorkService workService;
    @Operation(
            summary = "Создание работы",
            description = "При создании задаются название работы, даты ее начала и окончания, данные об исполнителе, и идентификатор" +
                    " проекта, к которому относится работа. Может быть задан также идентификатор контрольной точки, к которой привязана" +
                    " работа. Если у работы нет контрольной точки, в качестве идентификатора передается 0, или этот параметр не передается." +
                    " Процент выполнения работы при создании автоматически устанавливаются равным 0. Дата начала работы не может быть" +
                    " позже даты ее окончания. Дата окончания работы не может быть позже даты контрольной точки, к которой работа" +
                    " привязана."
    )
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public WorkDto createWork(@RequestBody @Valid WorkCreationDto workCreationDto) {
        return workService.createWork(workCreationDto);
    }

    @Operation(
            summary = "Удаление работы",
            description = "Удаляется работа с заданным идентификатором, передаваемым в пути запроса."
    )
    @DeleteMapping("/{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteWork(@PathVariable @Schema(description = "Идентификатор работы", example = "1") long id) {
        workService.deleteWork(id);
    }

    @Operation(
            summary = "Изменение работы",
            description = "Можно изменить название работы, даты ее начала и окончания, данные об исполнителе, процент выполнения, " + // TODO check the all variants of patching
                    "привязать ее к другому проекту. Могут быть изменены как все параметры, так и только некоторые из них. " +
                    "Чтобы не изменять такие параметры, как название, даты, данные об исполнителе, в запросе передается null, " +
                    "или параметры не передаются. Чтобы не изменять процент выполнения, в качестве значения передается 0; чтобы " +
                    "не изменять проект или контрольную точку, к которым привязана работа, в качестве идентификаторов также " +
                    "передается 0, или параметры не передаются. Дата начала работы не может быть позже даты ее окончания. Дата " +
                    "окончания работы не может быть позже даты контрольной точки, к которой работа привязана."
    )
    @PatchMapping("/patch")
    public WorkDto patchWork(@RequestBody @Valid WorkPatchDto workPatchDto) {
        return workService.patchWork(workPatchDto);
    }

    @Operation(
            summary = "Получение списка работ по заданному проекту",
            description = "Проект задается с помощью его идентификатора, передаваемого в пути запроса."
    )
    @GetMapping("/get_by_project/{projectId}")
    public List<WorkDto> getAllByProjectId(@PathVariable @Schema(description = "Идентификатор проекта", example = "1") long projectId) {
        return workService.getAllByProjectId(projectId);
    }

    @Operation(
            summary = "Получение списка работ для заданной контрольной точки",
            description = "Контрольная точка задается с помощью ее идентификатора, передаваемого в пути запроса."
    )
    @GetMapping("/get_by_checkpoint/{checkpointId}")
    public List<WorkDto> getAllByCheckpointId(@PathVariable @Schema(description = "Идентификатор контрольной точки", example = "1") long checkpointId) {
        return workService.getAllByCheckpointId(checkpointId);
    }
}
