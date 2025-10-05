package org.example.taxiservice.service.impl;

import lombok.AllArgsConstructor;
import org.example.taxiservice.model.Driver;
import org.example.taxiservice.model.Ride;
import org.example.taxiservice.model.User;
import org.example.taxiservice.repository.RideRepository;
import org.example.taxiservice.service.RideService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RideServiceImpl implements RideService {
    private final RideRepository rideRepository;

    @Override
    public Ride createRide(Ride ride) {
        return rideRepository.save(ride);
    }

    @Override
    public Optional<Ride> getRideById(Long id) {
        return rideRepository.findById(id);
    }

    @Override
    public List<Ride> getAllRides() {
        return rideRepository.findAll();
    }

    @Override
    public List<Ride> getAllRidesByDriver(Driver driver) {
        return rideRepository.findRidesByDriver(driver);
    }

    @Override
    public List<Ride> getAllRidesPassenger(User passenger) {
        return rideRepository.findByPassenger(passenger);
    }

    @Override
    public Ride updateRide(Ride ride) {
        return rideRepository.save(ride);
    }

    @Override
    public void deleteRide(Long id) {
        rideRepository.deleteById(id);
    }
}
