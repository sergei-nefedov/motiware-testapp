package pers.nefedov.motiwaretestapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProjectDto {
    private Long id;

    private String name;

    private String number;

    private String condition;

    private double averageCompletionPercentage;

    private String director;
}
