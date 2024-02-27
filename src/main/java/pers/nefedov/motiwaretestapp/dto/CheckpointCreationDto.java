package pers.nefedov.motiwaretestapp.dto;

import lombok.Getter;
import lombok.Setter;
import pers.nefedov.motiwaretestapp.models.Project;
import pers.nefedov.motiwaretestapp.models.Work;
@Getter
@Setter
public class CheckpointCreationDto {

    private String name;

    private String finishDate;

    private Project project;

    private Work work;
}
