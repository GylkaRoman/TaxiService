package org.example.taxiservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.taxiservice.dto.auth.AuthRequestDTO;
import org.example.taxiservice.dto.auth.AuthResponseDTO;
import org.example.taxiservice.dto.driver.DriverRequestDTO;
import org.example.taxiservice.model.Car;
import org.example.taxiservice.model.Driver;
import org.example.taxiservice.model.Role;
import org.example.taxiservice.model.User;
import org.example.taxiservice.repository.CarRepository;
import org.example.taxiservice.repository.DriverRepository;
import org.example.taxiservice.repository.UserRepository;
import org.example.taxiservice.security.JwtUtil;
import org.example.taxiservice.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final DriverRepository driverRepository;
    private final CarRepository carRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public void registerPassenger(AuthRequestDTO dto) {
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new RuntimeException("Пользователь уже существует");
        }

        User user = User.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .name(dto.getName())
                .surname(dto.getSurname())
                .phoneNumber(dto.getPhoneNumber())
                .balance(dto.getBalance() != null ? dto.getBalance() : BigDecimal.ZERO)
                .dateOfBirth(dto.getDateOfBirth())
                .role(Role.PASSENGER)
                .build();

        userRepository.save(user);
    }

    @Override
    public void registerDriver(DriverRequestDTO dto) {
        if (driverRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new RuntimeException("Драйвер уже существует");
        }

        Car car = carRepository.findById(dto.getCarId())
                .orElseThrow(() -> new RuntimeException("Car not found"));

        Driver driver = Driver.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .name(dto.getName())
                .surname(dto.getSurname())
                .phoneNumber(dto.getPhoneNumber())
                .licenceNumber(dto.getLicenceNumber())
                .rating(dto.getRating())
                .available(dto.isAvailable())
                .dateOfBirth(dto.getDateOfBirth())
                .role(Role.DRIVER)
                .car(car)
                .build();

        driverRepository.save(driver);
    }

    @Override
    public AuthResponseDTO login(AuthRequestDTO dto) {
        System.out.println("Login attempt: " + dto.getUsername());

        User user = userRepository.findByUsername(dto.getUsername()).orElse(null);

        if (user == null) {
            user = driverRepository.findByUsername(dto.getUsername()).orElse(null);
        }

        System.out.println("Found user: " + (user != null ? user.getUsername() : "null"));

        if (user == null) {
            throw new RuntimeException("Пользователь не найден");
        }

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            System.out.println("Invalid password for user: " + dto.getUsername());
            throw new RuntimeException("Неверный пароль");
        }

        String token = jwtUtil.generateToken(user.getUsername(), user.getRole().name());
        System.out.println("JWT generated for user: " + user.getUsername());

        AuthResponseDTO response = new AuthResponseDTO();
        response.setToken(token);
        return response;
    }
}
