package org.example.taxiservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.taxiservice.dto.driver.DriverRequestDTO;
import org.example.taxiservice.dto.driver.DriverResponseDTO;
import org.example.taxiservice.service.DriverService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @PostMapping
    public ResponseEntity<DriverResponseDTO> createDriver(@Valid @RequestBody DriverRequestDTO dto) {
        DriverResponseDTO driver = driverService.createDriver(dto);
        return ResponseEntity.ok(driver);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverResponseDTO> getDriver(@PathVariable Long id) {
        DriverResponseDTO driver = driverService.getDriverById(id);
        return ResponseEntity.ok(driver);
    }

    @GetMapping
    public ResponseEntity<List<DriverResponseDTO>> getAllDrivers() {
        List<DriverResponseDTO> drivers = driverService.getAllDrivers();
        return ResponseEntity.ok(drivers);
    }

    @GetMapping("/available")
    public ResponseEntity<List<DriverResponseDTO>> getAvailableDrivers() {
        List<DriverResponseDTO> drivers = driverService.getAvailableDrivers();
        return ResponseEntity.ok(drivers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DriverResponseDTO> updateDriver(@PathVariable Long id,
                                                          @Valid @RequestBody DriverRequestDTO dto) {
        DriverResponseDTO updated = driverService.updateDriver(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDriver(@PathVariable Long id) {
        driverService.deleteDriver(id);
        return ResponseEntity.noContent().build();
    }
}
