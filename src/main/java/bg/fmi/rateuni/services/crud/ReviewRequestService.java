package bg.fmi.rateuni.services.crud;

import bg.fmi.rateuni.models.ReviewRequest;
import bg.fmi.rateuni.repository.ReviewRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReviewRequestService {
    @Autowired
    private ReviewRequestRepository reviewRequestRepository;

    public List<ReviewRequest> getAllReviewRequests() {
        return reviewRequestRepository.findAll();
    }
    
    public Optional<ReviewRequest> getReviewRequestById(UUID id) {
        return reviewRequestRepository.findById(id);
    }
    
    public void createUpdateReviewRequest(ReviewRequest reviewRequest) {
        reviewRequestRepository.save(reviewRequest);
    }
}
