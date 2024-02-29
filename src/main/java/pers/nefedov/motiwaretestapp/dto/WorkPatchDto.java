package pers.nefedov.motiwaretestapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WorkPatchDto { //TODO validation
    @Schema(description = "Идентификатор работы", example = "1")
    @NotEmpty
    @Min(0)
    private Long id;

    @Schema(description = "Название работы", example = "Подготовка котлована")
    @Size(min = 1, max = 255)
    private String name;

    @Schema(description = "Дата начала работы", example = "01.01.2030")
    @Size(min = 10, max = 10)
    private String startDate;

    @Schema(description = "Дата окончания работы", example = "20.02.2030")
    @Size(min = 10, max = 10)
    private String finishDate;

    @Schema(description = "Процент выполнения работы", example = "15.5")
    @NotEmpty
    @Min(0)
    @Max(100)
    private double averageCompletionPercentage;

    @Schema(description = "Исполнитель работы", example = "Труба Н.Н.")
    @Size(min = 1, max = 255)
    private String implementer;

    @Schema(description = "Идентификатор проекта, к которому относится работа", example = "1")
    @Min(0)
    private long projectId;

    @Schema(description = "Идентификатор контрольной точки, к которой относится работа", example = "0")
    @Min(0)
    private long checkpointId;
}
