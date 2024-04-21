package bg.fmi.rateuni.models.base;

import lombok.Data;

@Data
public class University {
    private int id;
    private String name;
    private String rector;
    private String hqAddress;
}
