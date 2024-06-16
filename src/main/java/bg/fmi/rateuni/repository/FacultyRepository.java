package bg.fmi.rateuni.repository;


import bg.fmi.rateuni.models.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, UUID> {
    @Query("select f from Faculty f where f.university.id = :universityId")
    List<Faculty> findAllFacultiesByUniversityId(@Param("universityId")UUID universityId);
    
    @Query("select f from Faculty f where f.name = :name")
    Optional<Faculty> findByName(@Param("name")String name);
}
