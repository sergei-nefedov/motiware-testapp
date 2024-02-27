package pers.nefedov.motiwaretestapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectCreationDto {
    @Schema(description = "Название проекта", example = "Строительство дома")
    @NotEmpty
    @Size(min = 1, max = 255)
    private String name;

    @Schema(description = "Номер проекта", example = "001-B")
    @NotEmpty
    @Size(min = 1, max = 10)
    private String number;

    @Schema(description = "Руководитель проекта", example = "Иванов И.И.")
    @NotEmpty
    @Size(min = 1, max = 255)
    private String director;
}
