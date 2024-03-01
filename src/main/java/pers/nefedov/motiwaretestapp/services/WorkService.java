package pers.nefedov.motiwaretestapp.services;

import pers.nefedov.motiwaretestapp.dto.WorkCreationDto;
import pers.nefedov.motiwaretestapp.dto.WorkDto;
import pers.nefedov.motiwaretestapp.dto.WorkPatchDto;

import java.util.List;

public interface WorkService {
    WorkDto createWork(WorkCreationDto workCreationDto);

    void deleteWork(long id);

    WorkDto patchWork(WorkPatchDto workPatchDto);

    List<WorkDto> getAllByProjectId(long projectId);

    List<WorkDto> getAllByCheckpointId(long checkpointId);
}



