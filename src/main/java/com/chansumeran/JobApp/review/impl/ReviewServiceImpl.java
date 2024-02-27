package com.chansumeran.JobApp.review.impl;

import com.chansumeran.JobApp.company.Company;
import com.chansumeran.JobApp.company.CompanyService;
import com.chansumeran.JobApp.review.Review;
import com.chansumeran.JobApp.review.ReviewRepository;
import com.chansumeran.JobApp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review reviewRequest) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);

        Optional<Review> reviewOptional = reviews.stream().
                filter(review -> review.getId().equals(reviewId))
                .findFirst();

        if (reviewOptional.isPresent()) {
            Review reviewToUpdate = reviewOptional.get();

            reviewToUpdate.setTitle(reviewRequest.getTitle());
            reviewToUpdate.setDescription(reviewRequest.getDescription());
            reviewToUpdate.setRating(reviewRequest.getRating());

            reviewRepository.save(reviewToUpdate);
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);

        Optional<Review> reviewOptional = reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst();

        if (reviewOptional.isPresent()) {
            Review reviewToDelete = reviewOptional.get();
            reviewRepository.delete(reviewToDelete);
            return true;
        }

        return false;
    }
}
