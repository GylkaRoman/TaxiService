package org.example.taxiservice.repository;

import org.example.taxiservice.model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRepository extends JpaRepository<Ride, Long> {
}
