package pers.nefedov.motiwaretestapp.mappers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pers.nefedov.motiwaretestapp.dto.CheckpointCreationDto;
import pers.nefedov.motiwaretestapp.dto.CheckpointDto;
import pers.nefedov.motiwaretestapp.models.Checkpoint;


import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@Component
public class CheckpointMapperImpl implements CheckpointMapper {
    private final DateMapper dateMapper;
    @Override
    public Checkpoint mapToCheckpoint(CheckpointCreationDto checkpointCreationDto) {
        Checkpoint checkpoint = new Checkpoint();
        checkpoint.setName(checkpointCreationDto.getName());
        checkpoint.setFinishDate(dateMapper.mapToDate(checkpointCreationDto.getFinishDate()));
        checkpoint.setProject(checkpointCreationDto.getProject());
        return checkpoint;
    }

    @Override
    public CheckpointDto mapToCheckpointDto(Checkpoint checkpoint) {
        CheckpointDto checkpointDto = new CheckpointDto();
        checkpointDto.setId(checkpoint.getId());
        checkpointDto.setName(checkpoint.getName());
        checkpointDto.setFinishDate(dateMapper.mapDateToString(checkpoint.getFinishDate()));
        checkpointDto.setProject(checkpoint.getProject());
        checkpointDto.setWork(checkpoint.getWork());
        return checkpointDto;
    }

    @Override
    public List<CheckpointDto> mapTocheckpointDtoList(List<Checkpoint> checkpoints) {
        List<CheckpointDto> checkpointDtoList = new ArrayList<>(checkpoints.size());
        for (Checkpoint checkpoint : checkpoints) checkpointDtoList.add(this.mapToCheckpointDto(checkpoint));
        return checkpointDtoList;
    }
}
