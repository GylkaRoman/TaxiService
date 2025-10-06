package org.example.taxiservice.mapper;

import org.example.taxiservice.dto.driver.DriverRequestDTO;
import org.example.taxiservice.dto.driver.DriverResponseDTO;
import org.example.taxiservice.model.Car;
import org.example.taxiservice.model.Driver;
import org.springframework.stereotype.Component;

@Component
public class DriverMapper {
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
        return dto;
    }
}
