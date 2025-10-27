package org.example.taxiservice.mapper;

import org.example.taxiservice.dto.driver.DriverRequestDTO;
import org.example.taxiservice.dto.driver.DriverResponseDTO;
import org.example.taxiservice.model.Car;
import org.example.taxiservice.model.Driver;
import org.example.taxiservice.model.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DriverMapper {

    private final PasswordEncoder passwordEncoder;

    public DriverMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Driver toEntity(DriverRequestDTO dto, Car car) {
        if (dto == null) return null;

        Driver driver = new Driver();
        driver.setName(dto.getName());
        driver.setSurname(dto.getSurname());
        driver.setPhoneNumber(dto.getPhoneNumber());
        driver.setLicenceNumber(dto.getLicenceNumber());
        driver.setAvailable(dto.isAvailable());
        driver.setRating(dto.getRating());
        driver.setCar(car);
        driver.setUsername(dto.getUsername());
        driver.setPassword(passwordEncoder.encode(dto.getPassword()));
        driver.setDateOfBirth(dto.getDateOfBirth());
        driver.setRole(Role.DRIVER);
        return driver;
    }

    public DriverResponseDTO toDTO(Driver driver) {
        if (driver == null) return null;

        DriverResponseDTO dto = new DriverResponseDTO();
        dto.setId(driver.getId());
        dto.setName(driver.getName());
        dto.setSurname(driver.getSurname());
        dto.setPhoneNumber(driver.getPhoneNumber());
        dto.setLicenceNumber(driver.getLicenceNumber());
        dto.setAvailable(driver.isAvailable());
        dto.setRating(driver.getRating());
        dto.setCarId(driver.getCar() != null ? driver.getCar().getId() : null);
        dto.setUsername(driver.getUsername());
        dto.setDateOfBirth(driver.getDateOfBirth());
        dto.setRole(driver.getRole());
        return dto;
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }
}
