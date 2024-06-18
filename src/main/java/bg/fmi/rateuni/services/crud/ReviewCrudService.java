package bg.fmi.rateuni.services.crud;

import bg.fmi.rateuni.models.Review;
import bg.fmi.rateuni.models.ReviewRequest;
import bg.fmi.rateuni.models.User;
import bg.fmi.rateuni.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReviewCrudService {
    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getReviewsByDisciplineId(UUID disciplineId) {
        return reviewRepository.findReviewsByDisciplineId(disciplineId);
    }

    public Optional<Review> getReviewByID(UUID id) {
        return reviewRepository.findById(id);
    }

    public void createUpdateReview(Review review) {
        reviewRepository.save(review);
    }

    public User getUserByReviewId(UUID userId, UUID disciplineId) {
        return reviewRepository.findReviewForDiscipline(userId, disciplineId);
    }

    public void addRequestToReview(UUID reviewId, ReviewRequest reviewRequest) {
        reviewRepository.addRequestToReviewById(reviewId, reviewRequest);
    }

    public ReviewRequest getReviewRequestByReviewId(UUID reviewId) {
        return reviewRepository.findReviewRequestByReviewId(reviewId);
    }

    public void deleteReview(UUID id) {
        reviewRepository.deleteById(id);
    }
}
