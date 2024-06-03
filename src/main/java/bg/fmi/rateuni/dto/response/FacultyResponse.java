package bg.fmi.rateuni.dto.response;

import lombok.Data;

import java.util.UUID;

@Data
public class FacultyResponse {
    private UUID id;
    private String name;
    private String dean;
    private String address;
}
