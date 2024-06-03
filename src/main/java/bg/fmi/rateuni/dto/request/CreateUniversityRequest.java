package bg.fmi.rateuni.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateUniversityRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String rector;
    @NotBlank
    private String hqAddress;
}
