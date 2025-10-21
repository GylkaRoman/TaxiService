package org.example.taxiservice.service;

import org.example.taxiservice.model.TaxiType;

public interface FareCalculationService {
    double calculateFare(double distanceKm, TaxiType taxiType);
}
