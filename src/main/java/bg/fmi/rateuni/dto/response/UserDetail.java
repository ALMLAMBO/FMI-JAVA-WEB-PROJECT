package bg.fmi.rateuni.dto.response;

import bg.fmi.rateuni.models.Discipline;
import lombok.Data;

import java.util.UUID;

@Data
public class UserDetail {
    private UUID id;
    private String email;
    private String facultyNumber;
    private String universityName;
    private String facultyName;
    private String programmeName;
    private List<DisciplineResponse> disciplines;
}
