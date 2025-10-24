package org.example.taxiservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.taxiservice.dto.auth.AuthRequestDTO;
import org.example.taxiservice.dto.auth.AuthResponseDTO;
import org.example.taxiservice.model.Role;
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
            return ResponseEntity.ok(token); // возвращаем JSON с токеном
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(e.getMessage()); // возвращаем причину ошибки
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequestDTO dto,
                                           @RequestParam(defaultValue = "PASSENGER") String role) {
        try {
            authService.register(dto, Role.valueOf(role.toUpperCase()));
            return ResponseEntity.ok("Пользователь зарегистрирован!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
