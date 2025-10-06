package org.example.taxiservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.taxiservice.mapper.UserMapper;
import org.example.taxiservice.dto.user.UserRequestDTO;
import org.example.taxiservice.dto.user.UserResponseDTO;
import org.example.taxiservice.model.Role;
import org.example.taxiservice.model.User;
import org.example.taxiservice.repository.UserRepository;
import org.example.taxiservice.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDTO createUser(UserRequestDTO dto) {
        User user = userMapper.toEntity(dto);
        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return userMapper.toDTO(user);
    }

    @Override
    public List<UserResponseDTO> getAllUser() {
        return userRepository.findAll().stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {
        User existing = userRepository.findById(id).orElse(null);
        if(existing == null) return null;

        existing.setName(dto.getName());
        existing.setSurname(dto.getSurname());
        existing.setPhoneNumber(dto.getPhoneNumber());
        existing.setBalance(dto.getBalance());
        existing.setDateOfBirth(dto.getDateOfBirth());
        existing.setRole(dto.getRole() != null ? Role.valueOf(dto.getRole().toUpperCase()) : existing.getRole());
        return userMapper.toDTO(userRepository.save(existing));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
