package bg.fmi.rateuni.dto.response;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ProgrammeInfoResponse {
    private UUID idInfoResponse;
    private String title;
    private String description;
    List<DisciplineResponse> disciplines;
}
