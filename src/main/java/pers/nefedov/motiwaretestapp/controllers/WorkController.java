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
                    " проекта, к которому относится работа. Процент выполнения работы при создании устанавливаются равным 0."
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
                    "Чтобы не изменять такие параметры, как название, даты, данные об исполнителе, в запросе передается null. " +
                    "Чтобы не изменять процент выполнения или проект, в качестве значений передается 0."
    )
    @PatchMapping("/patch")
    public WorkDto patchWork(@RequestBody @Valid WorkPatchDto workPatchDto) {
        return workService.patchWork(workPatchDto);
    }

    @Operation(
            summary = "Получение списка работ по заданному проекту",
            description = "Проект задается с помощью его идентификатора, передаваемого в пути запроса."
    )
    @GetMapping("/get/{projectId}")
    public List<WorkDto> getAllByProjectId(@PathVariable @Schema(description = "Идентификатор проекта", example = "1") long projectId) {
        return workService.getAllByProjectId(projectId);
    }

    @GetMapping("/get/{checkpointId}")
    public List<WorkDto> getAllByCheckpointId(@PathVariable @Schema(description = "Идентификатор контрольной точки", example = "1") long checkpointId) {
        return workService.getAllByCheckpointId(checkpointId);
    }
    /* TODO
        Получение всех работ требуемой контрольной точки;
        Окончание работы не может быть больше окончания контрольной точки;
        Для работы может быть 1 проект и 1 контрольная точка
        Наименование контрольной точки уникальное для проекта
        - Загрузка контрольных точек из файла для требуемого проекта. Сопоставление
        идет по наименованию, без учета регистра. Если в загружаемом файле нет
        контрольной точки проекта, то необходимо удалить контрольную точку.
        - Создать тестовое приложение, демонстрирующее вызов REST API сервера c
        использованием http-протокола;
        - Сформировать нагрузочный тест, делающий не менее 5 запросов в секунду на
        чтение и изменение работ, а также на выполнение действий для проекта
        1. SQL-скрипт для создания БД;
        2. SQL-скрипт для наполнения БД тестовыми данными;
        3. Исходный код в zip-архиве c git репозиторием;
        4. Описание сборки и запуска;
        - Спроектировать структуру БД, создать БД и наполнить её тестовыми данными
        (не менее 10 тыс проектов и 100 тыс задач);
        - Завершен - все работы проекты выполнены (реализовать)
    */
}
