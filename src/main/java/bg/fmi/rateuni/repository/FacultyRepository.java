package bg.fmi.rateuni.repository;

import bg.fmi.rateuni.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, UUID> {

    @Modifying
    @Query("UPDATE Faculty f SET f.facultyPrograms = :facultyPrograms WHERE f.id = :facultyId")
    void addProgrammeToFaculty(@Param("facultyId") UUID facultyId,
                               @Param("facultyPrograms") Set<Programme> facultyPrograms);

    @Query("SELECT f.facultyPrograms FROM Faculty f WHERE f.id = :id")
    List<Programme> findProgramsByFacultyId(@Param("id") UUID id);

    @Modifying
    @Query("UPDATE Faculty f SET f.users = :users WHERE f.id = :facultyId")
    void addUserToFacultyById(@Param("facultyId") UUID facultyId,
                            @Param("users") List<User> users);

    @Query("SELECT f.users FROM Faculty f WHERE f.id =:id")
    List<User> findUsersByFacultyId(@Param("id") UUID id);
}
