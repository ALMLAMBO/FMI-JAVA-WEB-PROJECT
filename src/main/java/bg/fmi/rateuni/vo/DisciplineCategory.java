package bg.fmi.rateuni.vo;

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

    public String getName() {
        return identifier;
    }
}
