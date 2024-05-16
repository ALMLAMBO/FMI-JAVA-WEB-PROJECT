package bg.fmi.rateuni.repository;

import bg.fmi.rateuni.models.ReviewRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReviewRequestRepository extends JpaRepository<ReviewRequest, UUID> {
}
