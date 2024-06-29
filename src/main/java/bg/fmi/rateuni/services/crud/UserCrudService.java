package bg.fmi.rateuni.services.crud;

import bg.fmi.rateuni.models.*;
import bg.fmi.rateuni.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserCrudService {
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
    
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
    
    public void createUserRequest(UUID id, UserRequest userRequest) {
        userRepository.addUserRequest(id, userRequest);
    }
    
    UserRequest getUserRequestByUserId(UUID id) {
        return userRepository.findUserRequestByUserId(id);
    }
    
    public void addReviewToUser(UUID id, Review review) {
        User user = getUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        
        user.getReviews().add(review);
        userRepository.save(user);
    }
    
    public List<Review> getReviewsByUserId(UUID id) {
        return userRepository.findAllReviewsByUserId(id);
    }
    
    public void addReviewRequestToUser(UUID id, ReviewRequest reviewRequest) {
        User user = getUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        
        user.getReviewRequests().add(reviewRequest);
        userRepository.save(user);
    }
    
    public List<ReviewRequest> getReviewRequestsByUserId(UUID id) {
        return userRepository.findAllReviewRequestsByUserId(id);
    }
    
    public List<Role> getUserRoles(UUID id) {
        return userRepository.findUserRoles(id);
    }
    
    public void addUserDiscipline(UUID id, Discipline discipline) {
        User user = getUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        
        user.getUserDisciplines().add(discipline);
        userRepository.save(user);
    }
    
    public List<Discipline> getDisciplinesByUserId(UUID id) {
        return userRepository.findAllDisciplinesByUserId(id);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}