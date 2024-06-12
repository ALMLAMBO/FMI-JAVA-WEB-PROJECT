package bg.fmi.rateuni.repository;

import bg.fmi.rateuni.models.Faculty;
import bg.fmi.rateuni.models.University;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface UniversityRepository extends JpaRepository<University, UUID> {
    @Modifying
    @Query("UPDATE universities u SET u.faculties = :faculties WHERE universities.id = :id")
    void addFacultyToUniversityById(@Param("id") UUID id,
                                    @Param("faculties") Set<Faculty> faculties);

    @Query("SELECT u.faculties FROM universities u WHERE universities.id = :id")
    Set<Faculty> findAllFacultiesByUniversityId(@Param("id") UUID id);
}