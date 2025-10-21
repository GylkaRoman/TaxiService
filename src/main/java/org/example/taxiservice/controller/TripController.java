package org.example.taxiservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.taxiservice.model.TaxiType;
import org.example.taxiservice.service.DistanceService;
import org.example.taxiservice.service.FareCalculationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class TripController {

    private final DistanceService distanceService;
    private final FareCalculationService fareService;

    @GetMapping("/trip")
    public Map<String, Object> calculateTrip(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam TaxiType taxiType
    ) throws Exception {
        double distanceKm = distanceService.getDistanceInKm(from, to);
        double fare = fareService.calculateFare(distanceKm, taxiType);

        return Map.of(
                "from", from,
                "to", to,
                "taxiType", taxiType,
                "distanceKm", String.format("%.2f", distanceKm),
                "fare", String.format("%.2f", fare)
        );
    }
}
