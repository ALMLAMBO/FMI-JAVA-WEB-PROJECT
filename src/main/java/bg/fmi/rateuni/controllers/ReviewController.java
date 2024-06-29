package bg.fmi.rateuni.controllers;

import bg.fmi.rateuni.dto.request.CreateReviewRequest;
import bg.fmi.rateuni.dto.response.BaseResponse;
import bg.fmi.rateuni.dto.response.ReviewResponse;
import bg.fmi.rateuni.services.business.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponse> getDisciplineById(@PathVariable UUID id) {
        return ResponseEntity.ok(reviewService.getReviewById(id));
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<List<ReviewResponse>> getReviewsForDiscipline(@PathVariable UUID disciplineId) {
        return ResponseEntity.ok(reviewService.getReviewsByDisciplineId(disciplineId));
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createReview(@RequestBody CreateReviewRequest createReviewRequest) {
        return ResponseEntity.ok(reviewService.createReview(createReviewRequest));
    }
}
