package com.chansumeran.JobApp.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<String> createReview(@PathVariable Long companyId, @RequestBody ReviewRequestDto reviewRequestDto) {
        boolean isCreated = reviewService.createReview(companyId, reviewRequestDto);

        if (isCreated) {
            return new ResponseEntity<>("Review added successfully", HttpStatus.CREATED);
        }

        return new ResponseEntity<>("Review was not added", HttpStatus.OK);
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId) {

        List<Review> reviewsOfCompany = reviewService.getAllReviewsOfCompany(companyId);

        if (reviewsOfCompany.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(reviewsOfCompany, HttpStatus.OK);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewOfSpecificCompany(@PathVariable Long companyId,
                                                             @PathVariable Long reviewId) {
        return new ResponseEntity<>(reviewService.getReviewOfSpecificCompany(companyId, reviewId), HttpStatus.OK);
    }

    @PutMapping("reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId,
                                               @PathVariable Long reviewId,
                                               @RequestBody ReviewRequestDto reviewRequest) {
        boolean isUpdated = reviewService.updateReview(companyId, reviewId, reviewRequest);

        if (isUpdated) {
            return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
        }

        return new ResponseEntity<>("Review was not updated", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId,
                                               @PathVariable Long reviewId) {
        boolean isDeleted = reviewService.deleteReview(companyId, reviewId);

        if (isDeleted) {
            return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
        }

        return new ResponseEntity<>("Review was not deleted", HttpStatus.NOT_FOUND);
    }


}
