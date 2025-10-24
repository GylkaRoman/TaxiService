package org.example.taxiservice.service;

import org.example.taxiservice.dto.auth.AuthRequestDTO;
import org.example.taxiservice.dto.auth.AuthResponseDTO;
import org.example.taxiservice.model.Role;

public interface AuthService {
    void register(AuthRequestDTO dto, Role role);
    AuthResponseDTO login(AuthRequestDTO dto);
}