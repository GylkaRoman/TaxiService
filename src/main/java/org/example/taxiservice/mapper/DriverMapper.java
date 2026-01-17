package org.example.taxiservice.mapper;

import org.example.taxiservice.dto.driver.DriverRequestDTO;
import org.example.taxiservice.dto.driver.DriverResponseDTO;
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

    public Driver toEntity(DriverRequestDTO dto) {
        if (dto == null) return null;

        Driver driver = new Driver();
        driver.setName(dto.getName());
        driver.setSurname(dto.getSurname());
        driver.setPhoneNumber(dto.getPhoneNumber());
        driver.setUsername(dto.getUsername());
        driver.setPassword(passwordEncoder.encode(dto.getPassword()));
        driver.setDateOfBirth(dto.getDateOfBirth());
        driver.setRole(Role.DRIVER);
        driver.setAvailable(false);
        driver.setRating(0.0);
        return driver;
    }


    public DriverResponseDTO toDTO(Driver driver) {
        if (driver == null) return null;

        DriverResponseDTO dto = new DriverResponseDTO();
        dto.setId(driver.getId());
        dto.setName(driver.getName());
        dto.setSurname(driver.getSurname());
        dto.setPhoneNumber(driver.getPhoneNumber());
        dto.setCarId(driver.getCar() != null ? driver.getCar().getId() : null);
        dto.setUsername(driver.getUsername());
        dto.setDateOfBirth(driver.getDateOfBirth());
        dto.setRole(driver.getRole());


        dto.setLicenceNumber(driver.getLicenceNumber());
        dto.setRating(driver.getRating());
        dto.setAvailable(driver.isAvailable());

        return dto;
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }
}
