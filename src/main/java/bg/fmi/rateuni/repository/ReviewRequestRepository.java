package bg.fmi.rateuni.repository;

import bg.fmi.rateuni.models.ReviewRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReviewRequestRepository extends JpaRepository<ReviewRequest, UUID> {
    @Query("SELECT rr FROM ReviewRequest rr WHERE rr.user.id = :userId")
    Optional<ReviewRequest> findReviewRequestByUserId (@Param("userId") UUID userid);
}
