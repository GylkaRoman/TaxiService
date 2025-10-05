package org.example.taxiservice.repository;

import org.example.taxiservice.model.Driver;
import org.example.taxiservice.model.Ride;
import org.example.taxiservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RideRepository extends JpaRepository<Ride, Long> {
    List<Ride> findRidesByDriver(Driver driver);
    List<Ride> findByPassenger(User passenger);

}
