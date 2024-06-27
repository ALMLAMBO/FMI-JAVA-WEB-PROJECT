package bg.fmi.rateuni.services.business;

import bg.fmi.rateuni.dto.response.BaseResponse;
import bg.fmi.rateuni.mappers.ReviewMapper;
import bg.fmi.rateuni.models.ReviewRequest;
import bg.fmi.rateuni.services.crud.ReviewCrudService;
import bg.fmi.rateuni.services.crud.ReviewRequestCrudService;
import bg.fmi.rateuni.vo.RequestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReviewRequestService {
    @Autowired
    private ReviewRequestCrudService reviewRequestCrudService;

    @Autowired
    private ReviewCrudService reviewCrudService;

    public ReviewRequest getReviewRequestById(UUID reviewRequestId) {
        ReviewRequest reviewRequest = reviewRequestCrudService.getReviewRequestById(reviewRequestId).get();
        if(reviewRequest == null) {
            throw new IllegalArgumentException("Review request not found");
        }

        return reviewRequest;
    }

    public ReviewRequest getReviewRequestByUserId(UUID userId) {
        ReviewRequest reviewRequest = reviewRequestCrudService.getReviewRequestByUserId(userId).get();
        if(reviewRequest == null) {
            throw new IllegalArgumentException("Review request not found");
        }

        return reviewRequest;
    }

    public List<ReviewRequest> getActiveReviewRequests() {
        return reviewRequestCrudService.getAllReviewRequests()
                .stream()
                .filter(reviewRequest -> reviewRequest.getStatus() == RequestStatus.PENDING)
                .toList();
    }

    public void updateRequestStatus(UUID reviewRequestId, RequestStatus requestStatus) {
        ReviewRequest reviewRequest = reviewRequestCrudService.getReviewRequestById(reviewRequestId).get();
        reviewRequest.setStatus(requestStatus);
        this.reviewRequestCrudService.createUpdateReviewRequest(reviewRequest);
    }

}
