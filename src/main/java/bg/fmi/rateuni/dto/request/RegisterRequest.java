package bg.fmi.rateuni.dto.request;

import bg.fmi.rateuni.vo.UserGender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserGender gender;
    private String facultyNumber;
    private String universityName;
    private String facultyName;
    private String programmeName;
}
