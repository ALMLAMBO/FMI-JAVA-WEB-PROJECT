package bg.fmi.rateuni.repository;

import bg.fmi.rateuni.models.Faculty;
import bg.fmi.rateuni.models.Programme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProgrammeRepository extends JpaRepository<Programme, UUID> {
    @Query("select p from Programme p where p.faculty.id = :facultyId")
    List<Programme> findProgramsByFacultyId(@Param("facultyId") UUID facultyId);

    @Query("select p from Programme p where p.title = :title")
    Optional<Programme> findByTitle(@Param("title")String title);
}
