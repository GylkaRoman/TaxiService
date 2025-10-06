package org.example.taxiservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.taxiservice.model.Driver;
import org.example.taxiservice.model.Ride;
import org.example.taxiservice.model.User;
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
    public ResponseEntity<Ride> createRide(@RequestBody Ride ride) {
        return ResponseEntity.ok(rideService.createRide(ride));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ride> getRide(@PathVariable Long id) {
        return rideService.getRideById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Ride> getAllRides() {
        return rideService.getAllRides();
    }

    @GetMapping("/driver/{driverId}")
    public List<Ride> getAllRidesByDriver(@PathVariable Long driverId) {
        Driver driver = new Driver();
        driver.setId(driverId);
        return rideService.getAllRidesByDriver(driver);
    }

    @GetMapping("/passenger/{userId}")
    public List<Ride> getAllRidesByPassenger(@PathVariable Long userId) {
        User passenger = new User();
        passenger.setId(userId);
        return rideService.getAllRidesPassenger(passenger);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ride> updateRide(@PathVariable Long id, @RequestBody Ride ride) {
        ride.setId(id);
        return ResponseEntity.ok(rideService.updateRide(ride));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRide(@PathVariable Long id) {
        rideService.deleteRide(id);
        return ResponseEntity.noContent().build();
    }
}
