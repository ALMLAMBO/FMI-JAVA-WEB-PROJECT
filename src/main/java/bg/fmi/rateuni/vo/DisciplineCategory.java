package bg.fmi.rateuni.vo;

import com.fasterxml.jackson.annotation.JsonValue;

public enum DisciplineCategory {
    CSF("ОКН"),
    CSC("ЯКН"),
    CSP("КП"),
    MAT("М"),
    APM("ПМ"),
    OTHER("ДР");

private final String identifier;
    DisciplineCategory(String id) {
        this.identifier = id;
    }

    @JsonValue
    public String getName() {
        return identifier;
    }
}
