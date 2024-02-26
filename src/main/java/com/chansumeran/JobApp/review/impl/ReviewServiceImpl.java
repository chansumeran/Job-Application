package com.chansumeran.JobApp.review.impl;

import com.chansumeran.JobApp.company.Company;
import com.chansumeran.JobApp.company.CompanyService;
import com.chansumeran.JobApp.review.Review;
import com.chansumeran.JobApp.review.ReviewRepository;
import com.chansumeran.JobApp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public boolean createReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);

        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }

        return false;
    }

    @Override
    public List<Review> getAllReviewsOfCompany(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public Review getReviewOfSpecificCompany(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }
}
