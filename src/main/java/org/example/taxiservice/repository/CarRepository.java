package org.example.taxiservice.repository;

import org.example.taxiservice.model.Car;
import org.example.taxiservice.model.TaxiType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
        List<Car> findAllByTaxiType(TaxiType taxiType);
}
