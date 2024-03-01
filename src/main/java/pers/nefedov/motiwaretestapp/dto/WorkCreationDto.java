package pers.nefedov.motiwaretestapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkCreationDto {
    @Schema(description = "Название работы", example = "Подготовка участка")
    @NotEmpty
    @Size(min = 1, max = 255)
    private String name;

    @Schema(description = "Дата начала работы", example = "01.01.2024")
    private String startDate; // TODO validation

    @Schema(description = "Дата окончания работы", example = "20.02.2024")
    private String finishDate; // TODO validation

    @Schema(description = "Исполнитель работы", example = "Иванов П.П.")
    @NotEmpty
    @Size(min = 1, max = 255)
    private String implementer;

    @Schema(description = "Идентификатор проекта, к которому относится работа", example = "1")
    @NotEmpty
    @Min(0)
    private long projectId;

    @Schema(description = "Идентификатор контрольной точки, к которой относится работа", example = "0")
    @Min(0)
    private long checkpointId;
}
