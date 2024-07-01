package bg.fmi.rateuni.vo;

import com.fasterxml.jackson.annotation.JsonValue;

public enum DisciplineType {
    ELECTIVE("избираема"),
    MANDATORY("задължителна");

    private final String type;
    DisciplineType(String type) {
        this.type = type;
    }
    
    public String getType() {
        return type;
    }
}
