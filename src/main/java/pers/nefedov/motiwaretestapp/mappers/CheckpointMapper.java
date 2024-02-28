package pers.nefedov.motiwaretestapp.mappers;



import pers.nefedov.motiwaretestapp.dto.CheckpointCreationDto;
import pers.nefedov.motiwaretestapp.dto.CheckpointDto;
import pers.nefedov.motiwaretestapp.models.Checkpoint;

import java.util.List;

public interface CheckpointMapper {
    Checkpoint mapToCheckpoint(CheckpointCreationDto workCreationDto);
    CheckpointDto mapToCheckpointDto(Checkpoint checkpoint);
    List<CheckpointDto> mapToCheckpointDtoList(List<Checkpoint> checkpoints);
}
