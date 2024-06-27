package bg.fmi.rateuni.services.crud;

import bg.fmi.rateuni.models.UserRequest;
import bg.fmi.rateuni.repository.UserRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserRequestCrudService {
    @Autowired
    private UserRequestRepository userRequestRepository;
    
    public List<UserRequest> getAllUserRequests() {
        return userRequestRepository.findAll();
    }
    
    public Optional<UserRequest> getUserRequestById(UUID id) {
        return userRequestRepository.findById(id);
    }
    
    public void createUpdateUserRequest(UserRequest userRequest) {
        userRequestRepository.save(userRequest);
    }

    public Optional<UserRequest> getUserRequestByUserId(UUID id) {
        return userRequestRepository.findUserRequestByUserId(id);
    }
}
