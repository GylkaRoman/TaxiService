package org.example.taxiservice.service;

import org.example.taxiservice.dto.user.UserRequestDTO;
import org.example.taxiservice.dto.user.UserResponseDTO;

import java.util.List;

public interface UserService {
    UserResponseDTO createUser(UserRequestDTO dto);
    UserResponseDTO getUserById(Long id);
    UserResponseDTO updateUser(Long id, UserRequestDTO dto);
    List<UserResponseDTO> getAllUser();
    void deleteUser(Long id);
}
