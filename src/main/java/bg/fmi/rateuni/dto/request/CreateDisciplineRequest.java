package bg.fmi.rateuni.dto.request;

import bg.fmi.rateuni.vo.DisciplineCategory;
import bg.fmi.rateuni.vo.DisciplineType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateDisciplineRequest {
    @NotBlank
    private String name;
    @NotNull
    private double credits;
    @NotNull
    private DisciplineCategory category;
    @NotNull
    private DisciplineType type;
}
