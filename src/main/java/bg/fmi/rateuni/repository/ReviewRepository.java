package bg.fmi.rateuni.repository;

import bg.fmi.rateuni.models.Review;
import bg.fmi.rateuni.models.ReviewRequest;
import bg.fmi.rateuni.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {
    @Modifying
    @Query("UPDATE Review r SET r.reviewRequest = :reviewRequest WHERE r.id = :id")
    void addRequestToReviewById(@Param("id") UUID id,
                                @Param("reviewRequest") ReviewRequest reviewRequest);

    @Query("SELECT r.reviewRequest FROM Review r WHERE r.id = :id") 
    ReviewRequest findReviewRequestByReviewId(@Param("id") UUID id);
    
    @Query("select r from Review r where r.discipline.id = :disciplineId and r.visible = true")
    List<Review> findReviewsByDisciplineId(@Param("disciplineId") UUID disciplineId);

    @Query("SELECT u FROM Review r, Discipline d, User u WHERE u.id = :userId AND d.id = :disciplineId" +
            " AND u member of d.users AND r.discipline = d")
    User findReviewForDiscipline(@Param("userId") UUID userId, @Param("disciplineId") UUID disciplineId);
}
