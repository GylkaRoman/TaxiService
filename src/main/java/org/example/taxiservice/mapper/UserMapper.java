package org.example.taxiservice.mapper;

import org.example.taxiservice.dto.user.UserRequestDTO;
import org.example.taxiservice.dto.user.UserResponseDTO;
import org.example.taxiservice.model.Role;
import org.example.taxiservice.model.User;
import org.springframework.stereotype.Component;

import java.time.Period;

@Component
public class UserMapper {
    public User toEntity(UserRequestDTO dto) {
        if (dto == null) return null;
        User user = new User();
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setBalance(dto.getBalance());
        user.setDateOfBirth(dto.getDateOfBirth());
        user.setRole(Role.valueOf(dto.getRole().toUpperCase()));
        return user;
    }

    public UserResponseDTO toDTO(User user) {
        if (user == null) return null;
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setBalance(user.getBalance());
        dto.setDateOfBirth(user.getDateOfBirth());
        dto.setRole(user.getRole().name());
        dto.setAge(user.getDateOfBirth() != null ? Period.between(user.getDateOfBirth(),
                                                   java.time.LocalDate.now()).getYears() : 0);
        return dto;
    }
}
