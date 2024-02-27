package pers.nefedov.motiwaretestapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WorkDto {
    private Long id;

    private String name;

    private Date startDate;

    private Date finishDate;

    private double averageCompletionPercentage;

    private String implementer;

    private ProjectDto projectDto;
}
