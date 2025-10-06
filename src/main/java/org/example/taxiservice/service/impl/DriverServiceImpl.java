package org.example.taxiservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.taxiservice.dto.driver.DriverRequestDTO;
import org.example.taxiservice.dto.driver.DriverResponseDTO;
import org.example.taxiservice.mapper.DriverMapper;
import org.example.taxiservice.model.Car;
import org.example.taxiservice.model.Driver;
import org.example.taxiservice.repository.CarRepository;
import org.example.taxiservice.repository.DriverRepository;
import org.example.taxiservice.service.DriverService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;
    private final CarRepository carRepository;
    private final DriverMapper driverMapper;

    @Override
    public DriverResponseDTO createDriver(DriverRequestDTO dto) {
        Car car = carRepository.findById(dto.getCarId())
                .orElseThrow(() -> new RuntimeException("Car not found: " + dto.getCarId()));

        Driver driver = driverMapper.toEntity(dto, car);
        Driver saved = driverRepository.save(driver);
        return driverMapper.toDTO(saved);
    }

    @Override
    public DriverResponseDTO updateDriver(Long id, DriverRequestDTO dto) {
        Driver existing = driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found: " + id));

        Car car = carRepository.findById(dto.getCarId())
                .orElseThrow(() -> new RuntimeException("Car not found: " + dto.getCarId()));

        existing.setName(dto.getName());
        existing.setSurname(dto.getSurname());
        existing.setPhoneNumber(dto.getPhoneNumber());
        existing.setLicenceNumber(dto.getLicenceNumber());
        existing.setAvailable(dto.isAvailable());
        existing.setRating(dto.getRating());
        existing.setCar(car);

        return driverMapper.toDTO(driverRepository.save(existing));
    }

    @Override
    public DriverResponseDTO getDriverById(Long id) {
        return driverRepository.findById(id)
                .map(driverMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Driver not found: " + id));
    }

    @Override
    public List<DriverResponseDTO> getAllDrivers() {
        return driverRepository.findAll().stream()
                .map(driverMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DriverResponseDTO> getAvailableDrivers() {
        return driverRepository.findAllByAvailableTrue().stream()
                .map(driverMapper::toDTO)
                .toList();
    }

    @Override
    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }
}
