package com.chansumeran.JobApp.review;

import java.util.List;

public interface ReviewService {

    // create review
    boolean createReview(Long companyId, Review review);

    // get all reviews of company
    List<Review> getAllReviewsOfCompany(Long companyId);

    // get review of a specific company
    Review getReviewOfSpecificCompany(Long companyId, Long reviewId);

    boolean updateReview(Long companyId, Long reviewId, Review review);

    boolean deleteReview(Long companyId, Long reviewId);
}
