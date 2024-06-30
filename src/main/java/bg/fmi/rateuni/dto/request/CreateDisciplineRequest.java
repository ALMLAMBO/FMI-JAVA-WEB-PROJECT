package bg.fmi.rateuni.dto.request;

import bg.fmi.rateuni.vo.DisciplineCategory;
import bg.fmi.rateuni.vo.DisciplineType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateDisciplineRequest {
    private String name;
    private String description;
    private double credits;
    private DisciplineCategory category;
    private DisciplineType type;
    private String lecturer;
    private String assistants;
    private UUID programmeId;
}
