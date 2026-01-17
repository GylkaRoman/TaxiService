package org.example.taxiservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.taxiservice.dto.car.CarRequestDTO;
import org.example.taxiservice.dto.car.CarResponseDTO;
import org.example.taxiservice.mapper.CarMapper;
import org.example.taxiservice.model.Car;
import org.example.taxiservice.model.Driver;
import org.example.taxiservice.model.TaxiType;
import org.example.taxiservice.repository.CarRepository;
import org.example.taxiservice.repository.DriverRepository;
import org.example.taxiservice.service.CarService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final DriverRepository driverRepository;
    private final CarMapper carMapper;

    @Override
    public CarResponseDTO createCar(CarRequestDTO dto) {
        Car car = carMapper.toEntity(dto);

        if (dto.getDriverId() != null) {
            Driver driver = driverRepository.findById(dto.getDriverId())
                    .orElseThrow(() -> new RuntimeException("Driver not found: " + dto.getDriverId()));
            car.setDriver(driver);
            driver.setCar(car);
        }

        return carMapper.toDTO(carRepository.save(car));
    }

    @Override
    public CarResponseDTO updateCar(Long id, CarRequestDTO dto) {
        Car existing = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found: " + id));

        existing.setModel(dto.getModel());
        existing.setPlateNumber(dto.getPlateNumber());
        existing.setTaxiType(TaxiType.valueOf(dto.getTaxiType()));

        if (dto.getDriverId() != null) {
            Driver driver = driverRepository.findById(dto.getDriverId())
                    .orElseThrow(() -> new RuntimeException("Driver not found: " + dto.getDriverId()));
            existing.setDriver(driver);
            driver.setCar(existing);
        }

        return carMapper.toDTO(carRepository.save(existing));
    }

    @Override
    public CarResponseDTO getCarById(Long id) {
        return carRepository.findById(id)
                .map(carMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Car not found: " + id));
    }

    @Override
    public List<CarResponseDTO> getAllCars() {
        return carRepository.findAll().stream()
                .map(carMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CarResponseDTO> getAllCarsByType(TaxiType taxiType) {
        return carRepository.findAllByTaxiType(taxiType).stream()
                .map(carMapper::toDTO)
                .toList();
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
    @Override
    public CarResponseDTO createCarForDriver(CarRequestDTO dto, Driver driver) {
        Car car = carMapper.toEntity(dto);
        car.setDriver(driver);
        driver.setCar(car);
        driver.setAvailable(true);
        return carMapper.toDTO(carRepository.save(car));
    }

}
