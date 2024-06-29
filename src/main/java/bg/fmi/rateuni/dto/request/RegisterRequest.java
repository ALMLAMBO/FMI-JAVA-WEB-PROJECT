package bg.fmi.rateuni.dto.request;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    private String facultyNumber;
    private String universityName;
    private String facultyName;
    private String programmeName;
}
