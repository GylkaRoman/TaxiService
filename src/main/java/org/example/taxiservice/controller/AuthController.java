package org.example.taxiservice.controller;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.example.taxiservice.dto.auth.AuthRequestDTO;
import org.example.taxiservice.dto.auth.AuthResponseDTO;
import org.example.taxiservice.dto.driver.DriverRequestDTO;
import org.example.taxiservice.model.User;
import org.example.taxiservice.repository.UserRepository;
import org.example.taxiservice.security.JwtUtil;
import org.example.taxiservice.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO dto) {
        try {
            AuthResponseDTO tokens = authService.login(dto);
            return ResponseEntity.ok(tokens);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody Map<String, String> body) {
        try {
            String refreshToken = body.get("refreshToken");

            Claims claims = jwtUtil.extractClaims(refreshToken);

            if (!"REFRESH".equals(claims.get("type"))) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }

            String username = claims.getSubject();
            User user = userRepository.findByUsername(username).orElseThrow();

            String newAccess =
                    jwtUtil.generateAccessToken(
                            user.getUsername(),
                            user.getRole().name()
                    );

            return ResponseEntity.ok(Map.of("accessToken", newAccess));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register/passenger")
    public ResponseEntity<String> registerPassenger(@RequestBody AuthRequestDTO dto) {
        authService.registerPassenger(dto);
        return ResponseEntity.ok("Пассажир зарегистрирован!");
    }

    @PostMapping("/register/driver")
    public ResponseEntity<String> registerDriver(@RequestBody DriverRequestDTO dto) {
        authService.registerDriver(dto);
        return ResponseEntity.ok("Драйвер зарегистрирован!");
    }
}
