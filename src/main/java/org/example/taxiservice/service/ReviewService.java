package org.example.taxiservice.service;

import org.example.taxiservice.model.Driver;
import org.example.taxiservice.model.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    Review createReview(Review review);
    Optional<Review> getReviewById(Long id);
    List<Review> getAllReviews();
    List<Review> getReviewsByDriver(Driver driver);
    Review updateReview(Review review);
    void deleteReview(Long id);
}
