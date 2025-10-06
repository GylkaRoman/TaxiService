package org.example.taxiservice.repository;

import org.example.taxiservice.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    List<Driver> findAllByAvailableTrue();

}
