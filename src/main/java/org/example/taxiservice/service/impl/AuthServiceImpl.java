package org.example.taxiservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.taxiservice.dto.auth.AuthRequestDTO;
import org.example.taxiservice.dto.auth.AuthResponseDTO;
import org.example.taxiservice.model.Driver;
import org.example.taxiservice.model.Role;
import org.example.taxiservice.model.User;
import org.example.taxiservice.repository.DriverRepository;
import org.example.taxiservice.repository.UserRepository;
import org.example.taxiservice.security.JwtUtil;
import org.example.taxiservice.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final DriverRepository driverRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public void register(AuthRequestDTO dto, Role role) {
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new RuntimeException("Пользователь уже существует");
        }

        if (role == Role.DRIVER) {
            Driver driver = Driver.builder()
                    .username(dto.getUsername())
                    .password(passwordEncoder.encode(dto.getPassword()))
                    .role(Role.DRIVER)
                    .available(true)
                    .rating(0.0)
                    .build();
            driverRepository.save(driver);
        } else {
            User user = User.builder()
                    .username(dto.getUsername())
                    .password(passwordEncoder.encode(dto.getPassword()))
                    .role(role)
                    .build();
            userRepository.save(user);
        }
    }

    @Override
    public AuthResponseDTO login(AuthRequestDTO dto) {
        // Сначала ищем в users (PASSENGER и ADMIN)
        User user = userRepository.findByUsername(dto.getUsername()).orElse(null);

        // Если не нашли, ищем в драйверах
        if (user == null) {
            user = driverRepository.findByUsername(dto.getUsername()).orElseThrow(
                    () -> new RuntimeException("Пользователь не найден")
            );
        }

        // Проверка пароля
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Неверный пароль");
        }

        // Генерация JWT
        String token = jwtUtil.generateToken(user.getUsername(), user.getRole().name());

        AuthResponseDTO resp = new AuthResponseDTO();
        resp.setToken(token);
        return resp;
    }


}
