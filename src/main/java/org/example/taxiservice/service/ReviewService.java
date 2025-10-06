package org.example.taxiservice.service;

import org.example.taxiservice.dto.review.ReviewRequestDTO;
import org.example.taxiservice.dto.review.ReviewResponseDTO;

import java.util.List;

public interface ReviewService {
    ReviewResponseDTO createReview(ReviewRequestDTO dto);
    ReviewResponseDTO updateReview(Long id, ReviewRequestDTO dto);
    ReviewResponseDTO getReviewById(Long id);
    List<ReviewResponseDTO> getAllReviews();
    List<ReviewResponseDTO> getReviewsByDriver(Long driverId);
    void deleteReview(Long id);
}
