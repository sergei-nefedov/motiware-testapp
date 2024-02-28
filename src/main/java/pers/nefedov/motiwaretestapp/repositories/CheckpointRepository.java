package pers.nefedov.motiwaretestapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import pers.nefedov.motiwaretestapp.models.Checkpoint;
import pers.nefedov.motiwaretestapp.models.Project;

import java.util.List;

public interface CheckpointRepository extends JpaRepository<Checkpoint, Long> {
    List<Checkpoint> findByProject_Id(@NonNull Long id);

    void deleteByProject(@NonNull Project project);
}
