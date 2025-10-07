package org.example.taxiservice.mapper;

import org.example.taxiservice.dto.ride.RideRequestDTO;
import org.example.taxiservice.dto.ride.RideResponseDTO;
import org.example.taxiservice.model.Car;
import org.example.taxiservice.model.Driver;
import org.example.taxiservice.model.Ride;
import org.example.taxiservice.model.User;
import org.springframework.stereotype.Component;

@Component
public class RideMapper {
    public Ride toEntity(RideRequestDTO dto, User passenger, Driver driver, Car car) {
        if (dto == null) return null;
        Ride ride = new Ride();
        ride.setPassenger(passenger);
        ride.setDriver(driver);
        ride.setCar(car);
        ride.setStartLocation(dto.getStartLocation());
        ride.setEndLocation(dto.getEndLocation());
        ride.setPrice(dto.getPrice());
        return ride;
    }

    public RideResponseDTO toDTO(Ride ride) {
        if (ride == null) return null;
        RideResponseDTO dto = new RideResponseDTO();
        dto.setId(ride.getId());
        dto.setPassengerId(ride.getPassenger() != null ? ride.getPassenger().getId() : null);
        dto.setDriverId(ride.getDriver() != null ? ride.getDriver().getId() : null);
        dto.setCarId(ride.getCar() != null ? ride.getCar().getId() : null);
        dto.setStartLocation(ride.getStartLocation());
        dto.setEndLocation(ride.getEndLocation());
        dto.setPrice(ride.getPrice());
        return dto;
    }
}
