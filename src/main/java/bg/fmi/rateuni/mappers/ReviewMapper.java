package bg.fmi.rateuni.mappers;

import bg.fmi.rateuni.dto.request.CreateReviewRequest;
import bg.fmi.rateuni.dto.response.ReviewResponse;
import bg.fmi.rateuni.models.Review;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewResponse mapToDto(Review review);
    Review mapFromDto(ReviewResponse reviewResponse);
    Review mapFromCreateReviewRequest(CreateReviewRequest createReviewRequest);
}
