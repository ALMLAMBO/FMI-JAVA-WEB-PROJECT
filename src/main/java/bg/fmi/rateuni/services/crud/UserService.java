package bg.fmi.rateuni.services.crud;

import bg.fmi.rateuni.models.User;
import bg.fmi.rateuni.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
}