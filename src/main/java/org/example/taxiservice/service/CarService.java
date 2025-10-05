package org.example.taxiservice.service;

import org.example.taxiservice.model.Car;
import org.example.taxiservice.model.TaxiType;

import java.util.List;
import java.util.Optional;

public interface CarService {
    Car createCar(Car car);
    Optional<Car> getCarById(Long id);
    List<Car> getAllCars(TaxiType type);
    Car updateCar(Car Car);
    void deleteCar(Long id);
}
