package bg.fmi.rateuni.dto.response;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class FacultyInfoResponse {
    private UUID idInfoResponse;
    private String name;
    private String dean;
    private String address;
    List<ProgrammeResponse> programs;
}
