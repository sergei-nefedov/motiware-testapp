package pers.nefedov.motiwaretestapp.services;

import pers.nefedov.motiwaretestapp.dto.CheckpointDto;
import pers.nefedov.motiwaretestapp.dto.LoadCheckpointsFromFileDto;
import pers.nefedov.motiwaretestapp.dto.WorkDto;

import java.util.List;

public interface CheckpointService {
    List<CheckpointDto> getAllCheckpoints();
    List<WorkDto> getWorksByCheckpoint(long checkpointId);

    void loadFromFile(LoadCheckpointsFromFileDto loadCheckpointsFromFileDto);

    List<CheckpointDto> getCheckpointsByProjectId(long projectId);
}


