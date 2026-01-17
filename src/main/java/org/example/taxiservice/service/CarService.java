package org.example.taxiservice.service;

import org.example.taxiservice.dto.car.CarRequestDTO;
import org.example.taxiservice.dto.car.CarResponseDTO;
import org.example.taxiservice.model.Driver;
import org.example.taxiservice.model.TaxiType;

import java.util.List;

public interface CarService {
    CarResponseDTO createCar(CarRequestDTO dto);
    CarResponseDTO createCarForDriver(CarRequestDTO dto, Driver driver); // новый метод
    CarResponseDTO updateCar(Long id, CarRequestDTO dto);
    CarResponseDTO getCarById(Long id);
    List<CarResponseDTO> getAllCars();
    List<CarResponseDTO> getAllCarsByType(TaxiType type);
    void deleteCar(Long id);
}
