package pers.nefedov.motiwaretestapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pers.nefedov.motiwaretestapp.models.Condition;
import pers.nefedov.motiwaretestapp.models.Project;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {

    List<Project> findByCondition(@NonNull Condition condition);

    @Override
    Optional<Project> findById(Long id);

    @Transactional
    @Modifying
    @Query("update Project p set p.condition = APPROVING where p.id = ?1")
    int updateConditionToApprovingById(@NonNull Long id);

    @Transactional
    @Modifying
    @Query("update Project p set p.condition = IMPLEMENTATION where p.id = ?1")
    int updateConditionToImplementationById(@NonNull Long id);

    @Transactional
    @Modifying
    @Query("update Project p set p.condition = REFINEMENT where p.id = ?1")
    int updateConditionToRefinementById(@NonNull Long id);
}
