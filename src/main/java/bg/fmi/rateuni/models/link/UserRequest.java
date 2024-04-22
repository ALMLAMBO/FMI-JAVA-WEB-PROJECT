package bg.fmi.rateuni.models.link;

import bg.fmi.rateuni.vo.RequestStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserRequest {
    private int id;
    private int userId;
    private String username;
    private String universityName;
    private String facultyName;
    private String programmeName;
    private String facultyNumber;
    private RequestStatus requestStatus;
    private String image;
    private LocalDateTime createdAt;
}
