package bg.fmi.rateuni.repository;

import bg.fmi.rateuni.models.Discipline;
import bg.fmi.rateuni.models.Programme;
import bg.fmi.rateuni.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, UUID> {
    @Modifying
    @Query("UPDATE Discipline d SET d.disciplineReviews = :reviews WHERE d.id = :disciplineId")
    void addReviewToDiscipline(@Param("disciplineId") UUID disciplineId, @Param("reviews") Set<Review> reviews);
    
    @Query("SELECT d.disciplineReviews FROM Discipline d WHERE d.id = :id")
    List<Review> findDisciplineReviewsById(@Param("id") UUID id);

    @Modifying
    @Query("UPDATE Discipline d SET d.programs = :programmes WHERE d.id = :disciplineId")
    void addDisciplineToProgramme(@Param("disciplineId") UUID disciplineId, @Param("programmes") Set<Programme> programmes);
    
    @Query("SELECT d.programs FROM Discipline d WHERE d.id = :id")
    List<Programme> findDisciplineProgramsById(@Param("id") UUID id);
    
    @Modifying
    @Query("UPDATE Discipline d SET d.users = :users WHERE d.id = :disciplineId")
    void addUserToDiscipline(@Param("disciplineId") UUID disciplineId, @Param("users") Set<Programme> users);
    
    @Query("SELECT d.users FROM Discipline d WHERE d.id = :id")
    List<Programme> findDisciplineUsersById(@Param("id") UUID id);
}
