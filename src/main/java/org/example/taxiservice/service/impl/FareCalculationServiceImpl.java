package org.example.taxiservice.service.impl;

import org.example.taxiservice.model.TaxiType;
import org.example.taxiservice.service.FareCalculationService;
import org.springframework.stereotype.Service;

@Service
public class FareCalculationServiceImpl implements FareCalculationService {

    @Override
    public double calculateFare(double distanceKm, TaxiType type) {
        double baseRate;

        switch (type) {
            case STANDARD -> baseRate = 5.0;
            case COMFORT -> baseRate = 7.5;
            case BUSINESS -> baseRate = 10.0;
            case PREMIUM -> baseRate = 15.0;
            case LUXURY -> baseRate = 25.0;
            default -> baseRate = 5.0;
        }

        double baseFare = 30.0;
        return baseFare + distanceKm * baseRate;
    }
}
