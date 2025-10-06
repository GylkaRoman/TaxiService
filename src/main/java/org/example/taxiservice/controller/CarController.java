package org.example.taxiservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.taxiservice.model.Car;
import org.example.taxiservice.model.TaxiType;
import org.example.taxiservice.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        return ResponseEntity.ok(carService.createCar(car));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCar(@PathVariable Long id) {
        return carService.getCarById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Car> getAllCars(@PathVariable Car car) {
        return carService.getAllCars(car);
    }

    @GetMapping("/type/{type}")
    public List<Car> getAllCarsByType(@PathVariable TaxiType type) {
        return carService.getAllCarsByType(type);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car car) {
        car.setId(id);
        return ResponseEntity.ok(carService.updateCar(car));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }
}
