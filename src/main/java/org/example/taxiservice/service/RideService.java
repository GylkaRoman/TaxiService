package org.example.taxiservice.service;

import org.example.taxiservice.model.Driver;
import org.example.taxiservice.model.Ride;
import org.example.taxiservice.model.User;

import java.util.List;
import java.util.Optional;

public interface RideService {
    Ride createRide(Ride ride);
    Optional<Ride> getRideById(Long id);
    List<Ride> getAllRides();
    List<Ride> getAllRidesByDriver(Driver driver);
    List<Ride> getAllRidesPassenger(User passenger);
    Ride updateRide(Ride ride);
    void deleteRide(Long id);

}
