package bg.fmi.rateuni.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProgrammeDto {
    @NotBlank
    private String title;
    private String description;
}
