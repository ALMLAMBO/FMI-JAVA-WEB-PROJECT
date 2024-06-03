package bg.fmi.rateuni.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class DisciplineDto {
    @NotBlank
    private UUID id;
    @NotBlank
    private String name;
    private String description;
    @NotNull
    private double credits;
    @NotBlank
    private String disciplineCategory;
    @NotEmpty
    private String disciplineType;
    @NotBlank
    private String lecturer;
    private String assistants;
}
