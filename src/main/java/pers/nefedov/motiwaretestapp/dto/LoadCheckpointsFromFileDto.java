package pers.nefedov.motiwaretestapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoadCheckpointsFromFileDto {
    @Schema(description = "Идентификатор проекта", example = "1")
    @NotEmpty
    @Min(0)
    private long projectId;

    @Schema(description = "Путь к файлу", example = "E:/projects/Motiware/motiware-testapp/checkpoints.csv")
    @NotEmpty
    @Size(min = 1, max = 255)
    private String filePath;
}
