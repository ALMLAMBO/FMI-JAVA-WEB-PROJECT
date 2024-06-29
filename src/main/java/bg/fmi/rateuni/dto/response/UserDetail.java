package bg.fmi.rateuni.dto.response;

import lombok.Data;

import java.util.List;
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
    private List<ReviewResponse> reviews;
}
