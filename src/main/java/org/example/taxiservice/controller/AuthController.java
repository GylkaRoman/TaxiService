package org.example.taxiservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.taxiservice.dto.auth.AuthRequestDTO;
import org.example.taxiservice.dto.auth.AuthResponseDTO;
import org.example.taxiservice.dto.driver.DriverRequestDTO;
import org.example.taxiservice.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO dto) {
        try {
            AuthResponseDTO token = authService.login(dto);
            return ResponseEntity.ok(token);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/register/passenger")
    public ResponseEntity<String> registerPassenger(@Valid @RequestBody AuthRequestDTO dto) {
        try {
            authService.registerPassenger(dto);
            return ResponseEntity.ok("Пассажир зарегистрирован!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/register/driver")
    public ResponseEntity<String> registerDriver(@Valid @RequestBody DriverRequestDTO dto) {
        try {
            authService.registerDriver(dto);
            return ResponseEntity.ok("Драйвер зарегистрирован!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
