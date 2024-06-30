package bg.fmi.rateuni.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateProgrammeRequest {
    @NotBlank
    private String title;
    private String description;
    private UUID facultyId;
}
