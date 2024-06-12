package bg.fmi.rateuni.repository;

import bg.fmi.rateuni.models.Review;
import bg.fmi.rateuni.models.ReviewRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {
    @Modifying
    @Query("UPDATE reviews r SET r.reviewRequest = :reviewRequest WHERE r.id = :id")
    void addRequestToReviewById(@Param("id") UUID id,
                                @Param("reviewRequest") Set<ReviewRequest> reviewRequest);

    @Query("SELECT r.reviewRequest FROM reviews r WHERE r.id = :id")
    Set<ReviewRequest> findAllRequestsByReviewId(@Param("id") UUID id);
}
