package bg.fmi.rateuni.repository;

import bg.fmi.rateuni.models.Discipline;
import bg.fmi.rateuni.models.Review;
import bg.fmi.rateuni.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, UUID> {
    @Query("SELECT u FROM Discipline d, User u WHERE d.id = :id and u member of d.users")
    List<User> findAllUsersByDisciplineId(@Param("id") UUID id);

    @Query("SELECT r FROM Discipline d, Review r WHERE d.id = :id and r.discipline = d")
    List<Review> findAllRatingsByDisciplineId(@Param("id") UUID id);

    @Query("select d from Discipline d where d.programme.id = :programmeId")
    List<Discipline> findDisciplinesByProgrammeId(@Param("programmeId") UUID programmeId);

    @Query("select d from Discipline d where d.name = :name")
    Optional<Discipline> findByName(@Param("name")String name);
}
