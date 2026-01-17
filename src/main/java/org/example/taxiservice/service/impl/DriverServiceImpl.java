package org.example.taxiservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.taxiservice.dto.driver.DriverRequestDTO;
import org.example.taxiservice.dto.driver.DriverResponseDTO;
import org.example.taxiservice.mapper.DriverMapper;
import org.example.taxiservice.model.Driver;
import org.example.taxiservice.model.Role;
import org.example.taxiservice.repository.DriverRepository;
import org.example.taxiservice.service.DriverService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public DriverResponseDTO createDriver(DriverRequestDTO dto) {
        Driver driver = driverMapper.toEntity(dto);
        driver.setRole(Role.DRIVER);
        driver.setAvailable(false);
        driver.setRating(0.0);
        driver.setPassword(passwordEncoder.encode(dto.getPassword()));

        return driverMapper.toDTO(driverRepository.save(driver));
    }

    @Override
    public DriverResponseDTO updateDriver(Long id, DriverRequestDTO dto) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        driver.setName(dto.getName());
        driver.setSurname(dto.getSurname());
        driver.setPhoneNumber(dto.getPhoneNumber());
        driver.setUsername(dto.getUsername());
        driver.setDateOfBirth(dto.getDateOfBirth());

        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            driver.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        return driverMapper.toDTO(driverRepository.save(driver));
    }

    @Override
    public DriverResponseDTO getDriverById(Long id) {
        return driverRepository.findById(id)
                .map(driverMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Driver not found"));
    }

    @Override
    public List<DriverResponseDTO> getAllDrivers() {
        return driverRepository.findAll().stream()
                .map(driverMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DriverResponseDTO> getAvailableDrivers() {
        return driverRepository.findAll().stream()
                .filter(Driver::isAvailable)
                .map(driverMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }
}
