package org.example.taxiservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.taxiservice.dto.ride.RideRequestDTO;
import org.example.taxiservice.dto.ride.RideResponseDTO;
import org.example.taxiservice.mapper.RideMapper;
import org.example.taxiservice.model.Car;
import org.example.taxiservice.model.Driver;
import org.example.taxiservice.model.Ride;
import org.example.taxiservice.model.User;
import org.example.taxiservice.repository.CarRepository;
import org.example.taxiservice.repository.DriverRepository;
import org.example.taxiservice.repository.RideRepository;
import org.example.taxiservice.repository.UserRepository;
import org.example.taxiservice.service.RideService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RideServiceImpl implements RideService {

    private final RideRepository rideRepository;
    private final UserRepository userRepository;
    private final DriverRepository driverRepository;
    private final CarRepository carRepository;
    private final RideMapper rideMapper;

    @Override
    public RideResponseDTO createRide(RideRequestDTO dto) {
        User passenger = userRepository.findById(dto.getPassengerId())
                .orElseThrow(() -> new RuntimeException("Passenger not found: " + dto.getPassengerId()));
        Driver driver = driverRepository.findById(dto.getDriverId())
                .orElseThrow(() -> new RuntimeException("Driver not found: " + dto.getDriverId()));
        Car car = carRepository.findById(dto.getCarId())
                .orElseThrow(() -> new RuntimeException("Car not found: " + dto.getCarId()));

        Ride ride = rideMapper.toEntity(dto, passenger, driver, car);
        return rideMapper.toDTO(rideRepository.save(ride));
    }

    @Override
    public RideResponseDTO updateRide(Long id, RideRequestDTO dto) {
        Ride existing = rideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ride not found: " + id));

        User passenger = userRepository.findById(dto.getPassengerId())
                .orElseThrow(() -> new RuntimeException("Passenger not found: " + dto.getPassengerId()));
        Driver driver = driverRepository.findById(dto.getDriverId())
                .orElseThrow(() -> new RuntimeException("Driver not found: " + dto.getDriverId()));
        Car car = carRepository.findById(dto.getCarId())
                .orElseThrow(() -> new RuntimeException("Car not found: " + dto.getCarId()));

        existing.setPassenger(passenger);
        existing.setDriver(driver);
        existing.setCar(car);
        existing.setStartLocation(dto.getStartLocation());
        existing.setEndLocation(dto.getEndLocation());
        existing.setPrice(dto.getPrice());

        return rideMapper.toDTO(rideRepository.save(existing));
    }

    @Override
    public RideResponseDTO getRideById(Long id) {
        return rideRepository.findById(id)
                .map(rideMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Ride not found: " + id));
    }

    @Override
    public List<RideResponseDTO> getAllRides() {
        return rideRepository.findAll().stream()
                .map(rideMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteRide(Long id) {
        rideRepository.deleteById(id);
    }
    @Override
    public List<RideResponseDTO> findRidesByDriver(Long driverId) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new RuntimeException("Driver not found"));
        return rideRepository.findAllByDriver(driver)
                .stream()
                .map(rideMapper::toDTO)
                .toList();
    }

    @Override
    public List<RideResponseDTO> findRidesByPassenger(Long passengerId) {
        User passenger = userRepository.findById(passengerId)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));
        return rideRepository.findAllByPassenger(passenger)
                .stream()
                .map(rideMapper::toDTO)
                .toList();
    }
}