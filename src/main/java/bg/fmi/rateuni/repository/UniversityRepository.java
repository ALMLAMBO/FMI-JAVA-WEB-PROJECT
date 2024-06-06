package bg.fmi.rateuni.repository;

import bg.fmi.rateuni.models.Faculty;
import bg.fmi.rateuni.models.University;
import bg.fmi.rateuni.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface UniversityRepository extends JpaRepository<University, UUID> {
    @Query("SELECT u.users FROM University u WHERE u.id = :id")
    List<User> findUsersByUniversityId(@Param("id") UUID id);
    
    @Modifying
    @Query("UPDATE University u SET u.users = :users WHERE u.id = :id")
    void addUserToUniversity(@Param("id") UUID id, @Param("users") List<User> users);
    
    @Query("SELECT u.universityFaculties FROM University u WHERE u.id = :id")
    Set<Faculty> findFacultiesById(@Param("id") UUID id);
    
    @Modifying
    @Transactional
    @Query("UPDATE University u SET u.universityFaculties = :faculties WHERE u.id = :id")
    void addFacultyToUniversity(@Param("id") UUID id, @Param("faculties") Set<Faculty> faculties);
}
