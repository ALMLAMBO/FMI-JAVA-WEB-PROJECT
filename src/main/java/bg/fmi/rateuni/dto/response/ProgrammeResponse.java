package bg.fmi.rateuni.dto.response;

import lombok.Data;

import java.util.UUID;

@Data
public class ProgrammeResponse {
    private UUID idResponse;
    private String title;
    private String description;
}
