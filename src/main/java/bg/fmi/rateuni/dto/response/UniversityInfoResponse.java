package bg.fmi.rateuni.dto.response;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class UniversityInfoResponse {
    private UUID idInfoResponse;
    private String name;
    private String rector;
    private String hqAddress;
    List<FacultyResponse> faculties;
}
