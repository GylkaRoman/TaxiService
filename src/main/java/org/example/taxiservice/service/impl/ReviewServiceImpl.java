package org.example.taxiservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.taxiservice.dto.review.ReviewRequestDTO;
import org.example.taxiservice.dto.review.ReviewResponseDTO;
import org.example.taxiservice.mapper.ReviewMapper;
import org.example.taxiservice.model.Driver;
import org.example.taxiservice.model.Review;
import org.example.taxiservice.model.Ride;
import org.example.taxiservice.model.User;
import org.example.taxiservice.repository.DriverRepository;
import org.example.taxiservice.repository.ReviewRepository;
import org.example.taxiservice.repository.RideRepository;
import org.example.taxiservice.repository.UserRepository;
import org.example.taxiservice.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final DriverRepository driverRepository;
    private final RideRepository rideRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public ReviewResponseDTO createReview(ReviewRequestDTO dto) {
        User author = userRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found: " + dto.getAuthorId()));
        Driver driver = driverRepository.findById(dto.getDriverId())
                .orElseThrow(() -> new RuntimeException("Driver not found: " + dto.getDriverId()));
        Ride ride = rideRepository.findById(dto.getRideId())
                .orElseThrow(() -> new RuntimeException("Ride not found: " + dto.getRideId()));

        Review review = reviewMapper.toEntity(dto, author, driver, ride);
        return reviewMapper.toDTO(reviewRepository.save(review));
    }

    @Override
    public ReviewResponseDTO updateReview(Long id, ReviewRequestDTO dto) {
        Review existing = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found: " + id));

        User author = userRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found: " + dto.getAuthorId()));
        Driver driver = driverRepository.findById(dto.getDriverId())
                .orElseThrow(() -> new RuntimeException("Driver not found: " + dto.getDriverId()));
        Ride ride = rideRepository.findById(dto.getRideId())
                .orElseThrow(() -> new RuntimeException("Ride not found: " + dto.getRideId()));

        existing.setAuthor(author);
        existing.setDriver(driver);
        existing.setRide(ride);
        existing.setRating(dto.getRating());
        existing.setComment(dto.getComment());

        return reviewMapper.toDTO(reviewRepository.save(existing));
    }

    @Override
    public ReviewResponseDTO getReviewById(Long id) {
        return reviewRepository.findById(id)
                .map(reviewMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Review not found: " + id));
    }

    @Override
    public List<ReviewResponseDTO> getAllReviews() {
        return reviewRepository.findAll().stream()
                .map(reviewMapper::toDTO)
                .toList();
    }

    @Override
    public List<ReviewResponseDTO> getReviewsByDriver(Long driverId) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new RuntimeException("Driver not found"));
        return reviewRepository.findAllByDriver(driver)
                .stream()
                .map(reviewMapper::toDTO)
                .toList();
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}