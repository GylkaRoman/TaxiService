package org.example.taxiservice.mapper;

import org.example.taxiservice.dto.review.ReviewRequestDTO;
import org.example.taxiservice.dto.review.ReviewResponseDTO;
import org.example.taxiservice.model.Driver;
import org.example.taxiservice.model.Review;
import org.example.taxiservice.model.Ride;
import org.example.taxiservice.model.User;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {
    public Review toEntity(ReviewRequestDTO dto, User author, Driver driver, Ride ride) {
        if (dto == null) return null;
        Review review = new Review();
        review.setAuthor(author);
        review.setDriver(driver);
        review.setRide(ride);
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());
        return review;
    }

    public ReviewResponseDTO toDTO(Review review) {
        if (review == null) return null;
        ReviewResponseDTO dto = new ReviewResponseDTO();
        dto.setId(review.getId());
        dto.setAuthorId(review.getAuthor() != null ? review.getAuthor().getId() : null);
        dto.setDriverId(review.getDriver() != null ? review.getDriver().getId() : null);
        dto.setRideId(review.getRide() != null ? review.getRide().getId() : null);
        dto.setRating(review.getRating());
        dto.setComment(review.getComment());
        return dto;
    }
}
