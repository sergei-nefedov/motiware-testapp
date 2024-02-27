package pers.nefedov.motiwaretestapp.mappers;

import pers.nefedov.motiwaretestapp.dto.WorkCreationDto;
import pers.nefedov.motiwaretestapp.dto.WorkDto;
import pers.nefedov.motiwaretestapp.models.Work;

import java.util.List;

public interface WorkMapper {
    Work mapToWork(WorkCreationDto workCreationDto);
    WorkDto mapToWorkDto(Work work);
    List<WorkDto> mapToWorkDtoList(List<Work> works);
}
