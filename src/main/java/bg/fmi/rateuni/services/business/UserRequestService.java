package bg.fmi.rateuni.services.business;

import bg.fmi.rateuni.dto.response.BaseResponse;
import bg.fmi.rateuni.mappers.UserMapper;
import bg.fmi.rateuni.models.UserRequest;
import bg.fmi.rateuni.services.crud.UserRequestCrudService;
import bg.fmi.rateuni.vo.RequestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserRequestService {

    @Autowired
    private UserRequestCrudService userRequestCrudService;

    public UserRequest getUserRequestId(UUID userId) {
        UserRequest userRequest = userRequestCrudService.getUserRequestById(userId).get();
        if(userRequest == null) {
            throw new IllegalArgumentException("User request not found");
        }

        return userRequest;
    }

    public UserRequest getUserRequestByUserId(UUID userId) {
        UserRequest userRequest = userRequestCrudService.getUserRequestByUserId(userId).get();
        if(userRequest == null) {
            throw new IllegalArgumentException("User request not found");
        }

        return userRequest;
    }

    public List<UserRequest> getActiveUserRequests() {
        return userRequestCrudService.getAllUserRequests()
                .stream()
                .filter(userRequests -> userRequests.getRequestStatus() == RequestStatus.PENDING)
                .toList();
    }

    public void updateRequestStatus(UUID userRequestId, RequestStatus requestStatus) {
        UserRequest userRequest = userRequestCrudService.getUserRequestById(userRequestId).get();
        userRequest.setRequestStatus(requestStatus);
        this.userRequestCrudService.createUpdateUserRequest(userRequest);
    }
}
