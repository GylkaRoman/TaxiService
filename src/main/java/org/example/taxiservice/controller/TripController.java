package org.example.taxiservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.taxiservice.model.*;
import org.example.taxiservice.repository.DriverRepository;
import org.example.taxiservice.repository.RideRepository;
import org.example.taxiservice.repository.UserRepository;
import org.example.taxiservice.service.DistanceService;
import org.example.taxiservice.service.FareCalculationService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
public class TripController {

    private final DistanceService distanceService;
    private final FareCalculationService fareService;
    private final UserRepository userRepository;
    private final DriverRepository driverRepository;
    private final RideRepository rideRepository;

    @GetMapping("/trip")
    public Map<String, Object> calculateTrip(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam TaxiType taxiType,
            Authentication authentication // 👈 текущий пользователь из токена
    ) throws Exception {

        // 1️⃣ Получаем пассажира из токена
        String username = authentication.getName();
        User passenger = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        // 2️⃣ Находим случайного доступного водителя
        Driver driver = driverRepository.findFirstByAvailableTrue()
                .orElseThrow(() -> new RuntimeException("Нет доступных водителей"));

        // 3️⃣ Берём его машину
        Car car = driver.getCar();

        // 4️⃣ Рассчитываем расстояние и цену
        double distanceKm = distanceService.getDistanceInKm(from, to);
        double fare = fareService.calculateFare(distanceKm, taxiType);

        // 5️⃣ Сохраняем поездку
        Ride ride = Ride.builder()
                .passenger(passenger)
                .driver(driver)
                .car(car)
                .startLocation(from)
                .endLocation(to)
                .price(fare)
                .build();

        rideRepository.save(ride);

        // 6️⃣ Возвращаем расширенный ответ
        return Map.of(
                "rideId", ride.getId(),
                "from", from,
                "to", to,
                "distanceKm", String.format("%.2f", distanceKm),
                "fare", String.format("%.2f", fare),
                "taxiType", taxiType,
                "passenger", passenger.getUsername(),
                "driver", driver.getUsername(),
                "car", car != null ? car.getModel() + " (" + car.getPlateNumber() + ")" : "Без машины"
        );
    }
}
