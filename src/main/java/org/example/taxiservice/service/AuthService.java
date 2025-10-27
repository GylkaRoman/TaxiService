package org.example.taxiservice.service;

import org.example.taxiservice.dto.auth.AuthRequestDTO;
import org.example.taxiservice.dto.auth.AuthResponseDTO;
import org.example.taxiservice.dto.driver.DriverRequestDTO;

public interface AuthService {
    void registerPassenger(AuthRequestDTO dto);
    void registerDriver(DriverRequestDTO dto);
    AuthResponseDTO login(AuthRequestDTO dto);
}
