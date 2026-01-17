package org.example.taxiservice.service;

import org.example.taxiservice.dto.driver.DriverRequestDTO;
import org.example.taxiservice.dto.driver.DriverResponseDTO;

import java.util.List;

public interface DriverService {
    DriverResponseDTO createDriver(DriverRequestDTO dto); // регистрация водителя
    DriverResponseDTO updateDriver(Long id, DriverRequestDTO dto);
    DriverResponseDTO getDriverById(Long id);
    List<DriverResponseDTO> getAllDrivers();
    List<DriverResponseDTO> getAvailableDrivers();
    void deleteDriver(Long id);
}
