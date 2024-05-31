package bg.fmi.rateuni.services.crud;

import bg.fmi.rateuni.models.Review;
import bg.fmi.rateuni.models.ReviewRequest;
import bg.fmi.rateuni.models.Role;
import bg.fmi.rateuni.models.User;
import bg.fmi.rateuni.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }
    
    public void createUpdateUser(User user) {
        userRepository.save(user);
    }
    
    public List<Review> getUserReviews(UUID id) {
        return userRepository.findUserReviewsById(id);
    }
    
    public void addUserReview(UUID userId, Set<Review> reviews) {
        userRepository.addUserReview(userId, reviews);
    }
    
    public List<ReviewRequest> getUserReviewRequests(UUID id) {
        return userRepository.findUserReviewRequestsById(id);
    }
    
    public void addUserReviewRequest(UUID userId, Set<ReviewRequest> reviewRequests) {
        userRepository.addUserReviewRequest(userId, reviewRequests);
    }
    
    public void addUserRoles(UUID userId, Set<Role> roles) {
        userRepository.addUserRoles(userId, roles);
    }
}