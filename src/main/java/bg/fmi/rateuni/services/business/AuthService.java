package bg.fmi.rateuni.services.business;

import bg.fmi.rateuni.dto.request.LoginRequest;
import bg.fmi.rateuni.dto.request.RegisterRequest;
import bg.fmi.rateuni.dto.response.BaseResponse;
import bg.fmi.rateuni.mappers.UserMapper;
import bg.fmi.rateuni.models.*;
import bg.fmi.rateuni.services.crud.UserCrudService;
import bg.fmi.rateuni.services.crud.UserRequestCrudService;
import bg.fmi.rateuni.vo.RequestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserCrudService userCrudService;

    @Autowired
    private UserRequestCrudService userRequestCrudService;

    @Autowired
    private UserMapper userMapper;

    public BaseResponse registerUser(RegisterRequest registerRequest) {
        User user = userCrudService.getUserByEmail(registerRequest.getEmail()).get();
        if (user != null) {
            return new BaseResponse("User already exists");
        }

        UUID newId = UUID.randomUUID();
        user.setId(newId);
        user.setGender(registerRequest.getGender());
        userCrudService.createUpdateUser(user);

        UserRequest userRequest = new UserRequest(newId, registerRequest.getUsername(),
                registerRequest.getUniversityName(), registerRequest.getFacultyName(),
                registerRequest.getProgrammeName(), registerRequest.getFacultyNumber(),
                RequestStatus.PENDING,null,LocalDateTime.now(), user);

        userRequestCrudService.createUpdateUserRequest(userRequest);

        return new BaseResponse("User created successfully");
    }

    public BaseResponse loginUser(LoginRequest loginRequest) {
        User user = userCrudService.getUserByEmail(loginRequest.getEmail()).get();
        if (user == null || !user.getPassword().equals(loginRequest.getPassword())) {
            return new BaseResponse("Invalid email or password");
        }

        return new BaseResponse("Correct user credentials");
    }
}
