package bg.fmi.rateuni.dto.response;

import lombok.Data;

import java.util.UUID;

@Data
public class UniversityResponse {
    private UUID idInfoResponse;
    private String name;
    private String rector;
    private String hqAddress;
}
