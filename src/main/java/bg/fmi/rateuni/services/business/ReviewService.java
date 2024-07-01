package bg.fmi.rateuni.services.business;


import bg.fmi.rateuni.dto.request.CreateDisciplineRequest;
import bg.fmi.rateuni.dto.request.CreateReviewRequest;
import bg.fmi.rateuni.dto.response.BaseResponse;
import bg.fmi.rateuni.dto.response.DisciplineResponse;
import bg.fmi.rateuni.dto.response.ReviewResponse;
import bg.fmi.rateuni.mappers.ReviewMapper;

import bg.fmi.rateuni.models.Discipline;
import bg.fmi.rateuni.models.Review;
import bg.fmi.rateuni.models.ReviewRequest;
import bg.fmi.rateuni.models.User;
import bg.fmi.rateuni.services.crud.DisciplineCrudService;
import bg.fmi.rateuni.services.crud.ReviewCrudService;
import bg.fmi.rateuni.services.crud.ReviewRequestCrudService;
import bg.fmi.rateuni.services.crud.UserCrudService;
import bg.fmi.rateuni.vo.RequestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class ReviewService {
    @Autowired
    private ReviewCrudService reviewCrudService;

    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private UserCrudService userCrudService;

    @Autowired
    private DisciplineCrudService disciplineCrudService;

    @Autowired
    private ReviewRequestCrudService reviewRequestCrudService;

    public BaseResponse createReview(CreateReviewRequest reviewRequest) {
        User user = reviewCrudService.getUserByReviewId(reviewRequest.getUserId(), reviewRequest.getDisciplineId());
        if(user != null) {
            return new BaseResponse("User has already written a review");
        }

        Discipline discipline = disciplineCrudService.getDisciplineById(reviewRequest.getDisciplineId())
                .orElseThrow(() -> new IllegalArgumentException("Discipline does not exist"));
//        user = userCrudService.getUserById(reviewRequest.getUserId())
//                .orElseThrow(() -> new IllegalArgumentException("User does not exist"));
        Review review = reviewMapper.mapFromCreateReviewRequest(reviewRequest);
        review.setDiscipline(discipline);
//        review.setUser(user);
        reviewCrudService.createUpdateReview(review);
//        reviewRequestCrudService.createUpdateReviewRequest(new ReviewRequest(
//                UUID.randomUUID(), RequestStatus.PENDING, review, user));

        return new BaseResponse("Review submitted for additional check successfully");
    }

    public ReviewResponse getReviewById(UUID id) {
        Review review = reviewCrudService.getReviewByID(id).get();
        if(review == null) {
            throw new IllegalArgumentException("Review with id " + id + " not found");
        }

        ReviewResponse response = reviewMapper.mapToDto(review);
        return response;
    }

    public List<ReviewResponse> getReviewsByDisciplineId(UUID disciplineId) {
        return disciplineCrudService.getReviewsByDisciplineId(disciplineId)
                .stream()
                .map(review -> reviewMapper.mapToDto(review))
                .toList();
    }

    public List<ReviewResponse> getReviewsByUserId(UUID userId) {
        return userCrudService.getReviewsByUserId(userId)
                .stream()
                .map(review -> reviewMapper.mapToDto(review))
                .toList();
    }

    public BaseResponse deleteReview(UUID id) {
        Review review = reviewCrudService.getReviewByID(id).get();
        if(review == null) {
            return new BaseResponse("Review with id " + id + " not found");
        }

        reviewCrudService.deleteReview(id);
        return new BaseResponse("Review deleted successfully");
    }

    public List<ReviewResponse> getLatestSixReviews() {
        List<Review> reviews = reviewCrudService.getAllReviews();
        reviews.stream()
                .skip(Math.max(0, reviews.size() - 6))
                .collect(Collectors.toList());

        return reviews.stream()
                .map(review -> reviewMapper.mapToDto(review))
                .toList();
    }
}
