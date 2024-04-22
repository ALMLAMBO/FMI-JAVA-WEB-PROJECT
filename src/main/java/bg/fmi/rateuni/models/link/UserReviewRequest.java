package bg.fmi.rateuni.models.link;

import lombok.Data;

@Data
public class UserReviewRequest {
    private int userId;
    private int reviewRequestId;
}
