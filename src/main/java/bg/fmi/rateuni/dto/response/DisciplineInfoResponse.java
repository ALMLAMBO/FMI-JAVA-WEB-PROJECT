package bg.fmi.rateuni.dto.response;

import bg.fmi.rateuni.vo.DisciplineCategory;
import bg.fmi.rateuni.vo.DisciplineType;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class DisciplineInfoResponse {
    private UUID id;
    private String name;
    private double credits;
    private String description;
    private DisciplineCategory category;
    private DisciplineType type;
    private List<ReviewResponse> reviews;
}
