package bg.fmi.rateuni.models.base;

import bg.fmi.rateuni.vo.DisciplineCategory;
import bg.fmi.rateuni.vo.DisciplineType;
import lombok.Data;

@Data
public class Discipline {
    private int id;
    private String name;
    private String description;
    private double credits;
    private DisciplineCategory category; // ЯКН, ОКН, Мат...
    private DisciplineType type;
    private String lecturer;
    private String assistants;

}
