package org.example.taxiservice.repository;

import org.example.taxiservice.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    List<Driver> findAllByAvailableTrue();
    Optional<Driver> findByUsername(String username);
    Optional<Driver> findFirstByAvailableTrue();
}
