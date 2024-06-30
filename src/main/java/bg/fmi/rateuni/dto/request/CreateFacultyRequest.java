package bg.fmi.rateuni.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateFacultyRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String dean;
    @NotBlank
    private String address;
    private UUID universityId;
}
