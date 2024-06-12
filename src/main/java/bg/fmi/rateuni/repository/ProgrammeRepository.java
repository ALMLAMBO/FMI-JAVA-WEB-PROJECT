package bg.fmi.rateuni.repository;

import bg.fmi.rateuni.models.Discipline;
import bg.fmi.rateuni.models.Programme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface ProgrammeRepository extends JpaRepository<Programme, UUID> {
    @Modifying
    @Query("UPDATE Programme p SET p.disciplines = :disciplines WHERE p.id = :id")
    void addDisciplineToProgrammeById(@Param("id") UUID id,
                                      @Param("disciplines") Set<Discipline> disciplines);

    @Query("SELECT p.disciplines FROM Programme p WHERE p.id = :id")
    Set<Discipline> findAllDisciplinesByProgrammeId(@Param("id") UUID id);
}
