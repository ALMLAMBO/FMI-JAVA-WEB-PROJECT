package bg.fmi.rateuni.dto.response;

import lombok.Data;

@Data
public class DisciplineResponse {
    private UUID id;
    private String name;
    private double credits;
    private DisciplineCategory category;
    private DisciplineType type;
}
