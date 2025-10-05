package org.example.taxiservice.service.impl;

import lombok.AllArgsConstructor;
import org.example.taxiservice.model.Driver;
import org.example.taxiservice.model.Review;
import org.example.taxiservice.repository.ReviewRepository;
import org.example.taxiservice.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    @Override
    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public List<Review> getReviewsByDriver(Driver driver) {
        return reviewRepository.findReviewsByDriver(driver);
    }

    @Override
    public Review updateReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);

    }
}
