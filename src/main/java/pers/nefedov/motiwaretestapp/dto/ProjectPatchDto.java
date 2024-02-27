package pers.nefedov.motiwaretestapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProjectPatchDto {
    @Schema(description = "Идентификатор проекта", example = "1")
    private Long id;

    @Schema(description = "Название проекта", example = "Строительство дома по ул. Ленина")
    @NotEmpty
    @Size(min = 1, max = 255)
    private String name;

    @Schema(description = "Номер проекта", example = "002-AC")
    @NotEmpty
    @Size(min = 1, max = 10)
    private String number;

    @Schema(description = "Руководитель проекта", example = "Петров П.П.")
    @NotEmpty
    @Size(min = 1, max = 255)
    private String director;
}
