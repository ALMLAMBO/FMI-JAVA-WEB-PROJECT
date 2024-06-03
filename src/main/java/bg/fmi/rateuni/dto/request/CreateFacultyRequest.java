package bg.fmi.rateuni.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateFacultyRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String dean;
    @NotBlank
    private String address;
}
