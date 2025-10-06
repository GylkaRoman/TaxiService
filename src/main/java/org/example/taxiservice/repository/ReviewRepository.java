package org.example.taxiservice.repository;

import org.example.taxiservice.model.Driver;
import org.example.taxiservice.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByDriver(Driver driver);
}
