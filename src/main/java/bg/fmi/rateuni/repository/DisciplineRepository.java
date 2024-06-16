package bg.fmi.rateuni.repository;

import bg.fmi.rateuni.models.Discipline;
import bg.fmi.rateuni.models.Programme;
import bg.fmi.rateuni.models.Review;
import bg.fmi.rateuni.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, UUID> {
//    @Modifying
//    @Query("UPDATE Discipline d SET d.users = :users WHERE d.id = :id")
//    void addUsersToDisciplineById(@Param("id") UUID id,
//                                  @Param("users") Set<User> users);
//
//    @Query("SELECT d.users FROM Discipline d WHERE d.id = :id")
//    Set<User> findAllUsersByDisciplineId(@Param("id") UUID id);
//
//    @Modifying
//    @Query("UPDATE Discipline d SET d.ratings = :ratings WHERE d.id = :id")
//    void addRatingToDisciplineById(@Param("id") UUID id,
//                                   @Param("ratings") Set<Review> ratings);
//
//    @Query("SELECT d.ratings FROM Discipline d WHERE d.id = :id")
//    Set<Review> findAllRatingsByDisciplineId(@Param("id") UUID id);

    @Query("select d from Discipline d where d.programme.id = :programmeId")
    List<Discipline> findDisciplinesByProgrammeId(@Param("programmeId") UUID programmeId);

    @Query("select d from Discipline d where d.name = :name")
    Optional<Discipline> findByName(@Param("name")String name);
}
