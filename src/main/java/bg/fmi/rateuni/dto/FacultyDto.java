package bg.fmi.rateuni.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FacultyDto {
    @NotBlank
    private String nameDto;
    @NotBlank
    private String dean;
    @NotBlank
    private String address;
}
