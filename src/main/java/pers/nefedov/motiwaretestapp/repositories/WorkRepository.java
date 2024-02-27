package pers.nefedov.motiwaretestapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import pers.nefedov.motiwaretestapp.models.Work;

import java.util.List;
import java.util.Optional;

public interface WorkRepository extends JpaRepository<Work, Long> {

    @Override
    @NonNull
    Optional<Work> findById(@NonNull Long id);

    @Override
    @Transactional
    void deleteById(@NonNull Long id);

    List<Work> findByProject_Id(@NonNull Long id);
}
