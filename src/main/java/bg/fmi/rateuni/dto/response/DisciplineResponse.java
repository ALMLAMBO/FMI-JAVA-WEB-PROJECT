package bg.fmi.rateuni.dto.response;

import bg.fmi.rateuni.vo.DisciplineCategory;
import bg.fmi.rateuni.vo.DisciplineType;
import lombok.Data;

import java.util.UUID;

@Data
public class DisciplineResponse {
    private UUID id;
    private String name;
    private double credits;
    private DisciplineCategory category;
    private DisciplineType type;
}
