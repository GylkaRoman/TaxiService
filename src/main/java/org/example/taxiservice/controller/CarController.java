package org.example.taxiservice.controller;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.example.taxiservice.dto.car.CarRequestDTO;
import org.example.taxiservice.dto.car.CarResponseDTO;
import org.example.taxiservice.model.Driver;
import org.example.taxiservice.repository.DriverRepository;
import org.example.taxiservice.security.JwtUtil;
import org.example.taxiservice.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;
    private final JwtUtil jwtUtil;
    private final DriverRepository driverRepository;

    @PreAuthorize("hasRole('DRIVER')")
    @PostMapping
    public ResponseEntity<CarResponseDTO> createCar(
            @Valid @RequestBody CarRequestDTO dto,
            @RequestHeader("Authorization") String authHeader
    ) {

        String token = authHeader.replace("Bearer ", "");
        Claims claims = jwtUtil.extractClaims(token);
        String username = claims.getSubject();

        Driver driver = driverRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        CarResponseDTO car = carService.createCarForDriver(dto, driver);
        return ResponseEntity.ok(car);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarResponseDTO> getCar(@PathVariable Long id) {
        return ResponseEntity.ok(carService.getCarById(id));
    }

    @GetMapping
    public List<CarResponseDTO> getAllCars() {
        return carService.getAllCars();
    }
}
