package org.example.taxiservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.taxiservice.dto.ride.RideRequestDTO;
import org.example.taxiservice.dto.ride.RideResponseDTO;
import org.example.taxiservice.service.RideService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rides")
@RequiredArgsConstructor
public class RideController {

    private final RideService rideService;

    @PostMapping
    public ResponseEntity<RideResponseDTO> createRide(@Valid @RequestBody RideRequestDTO dto) {
        return ResponseEntity.ok(rideService.createRide(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RideResponseDTO> getRide(@PathVariable Long id) {
        return ResponseEntity.ok(rideService.getRideById(id));
    }

    @GetMapping
    public List<RideResponseDTO> getAllRides() {
        return rideService.getAllRides();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RideResponseDTO> updateRide(@PathVariable Long id, @Valid @RequestBody RideRequestDTO dto) {
        return ResponseEntity.ok(rideService.updateRide(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRide(@PathVariable Long id) {
        rideService.deleteRide(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/driver/{driverId}")
    public List<RideResponseDTO> getRidesByDriver(@PathVariable Long driverId) {
        return rideService.findRidesByDriver(driverId);
    }

    @GetMapping("/passenger/{passengerId}")
    public List<RideResponseDTO> getRidesByPassenger(@PathVariable Long passengerId) {
        return rideService.findRidesByPassenger(passengerId);
    }
}