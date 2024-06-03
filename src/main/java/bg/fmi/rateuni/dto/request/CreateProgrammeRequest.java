package bg.fmi.rateuni.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateProgrammeRequest {
    @NotBlank
    private String title;
    private String description;
}
