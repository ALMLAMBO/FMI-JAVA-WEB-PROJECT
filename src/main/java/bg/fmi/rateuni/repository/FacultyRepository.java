package bg.fmi.rateuni.repository;


import bg.fmi.rateuni.models.Faculty;
import bg.fmi.rateuni.models.Programme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, UUID> {
    @Modifying
    @Query("UPDATE faculties f SET f.programs = :programs WHERE f.id = :id")
    void addProgrammeToFacultyById(@Param("id") UUID id,
                                   @Param("programs") Set<Programme> programs);

    @Query("SELECT f.programs FROM faculties f WHERE f.id = :id")
    Set<Faculty> findAllProgramsByFacultyId(@Param("id") UUID id);
}
