package org.example.taxiservice.service;

import org.example.taxiservice.dto.ride.RideRequestDTO;
import org.example.taxiservice.dto.ride.RideResponseDTO;

import java.util.List;

public interface RideService {
    RideResponseDTO createRide(RideRequestDTO dto);
    RideResponseDTO updateRide(Long id, RideRequestDTO dto);
    RideResponseDTO getRideById(Long id);
    List<RideResponseDTO> getAllRides();
    List<RideResponseDTO> findRidesByDriver(Long driverId);
    List<RideResponseDTO> findRidesByPassenger(Long passengerId);
    void deleteRide(Long id);

}
