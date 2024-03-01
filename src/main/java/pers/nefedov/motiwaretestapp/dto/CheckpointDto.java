package pers.nefedov.motiwaretestapp.dto;

import lombok.Getter;
import lombok.Setter;
import pers.nefedov.motiwaretestapp.models.Project;
@Getter
@Setter
public class CheckpointDto {
    private long id;

    private String name;

    private String finishDate;

    private Project project;
}
