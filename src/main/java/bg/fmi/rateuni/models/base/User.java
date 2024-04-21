package bg.fmi.rateuni.models.base;

import bg.fmi.rateuni.vo.UserGender;
import lombok.Data;

@Data
public class User {
    private int id;
    private String email;
    private String password;
    private String facultyNumber;
    private UserGender gender;
}
