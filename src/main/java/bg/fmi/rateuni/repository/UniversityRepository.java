package bg.fmi.rateuni.repository;

import bg.fmi.rateuni.models.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UniversityRepository extends JpaRepository<University, UUID> {
    @Query("select u from University u where u.name = :name")
    Optional<University> findByName(String name);
}