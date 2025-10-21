package org.example.taxiservice.service;

public interface DistanceService {
    double getDistanceInKm(String from, String to) throws Exception;
}
