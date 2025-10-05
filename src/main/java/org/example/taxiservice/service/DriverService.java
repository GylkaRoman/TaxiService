package org.example.taxiservice.service;

import org.example.taxiservice.model.Driver;

import java.util.List;
import java.util.Optional;

public interface DriverService {
    Driver createDriver(Driver driver);
    Optional<Driver> getDriverById();
    List<Driver> getAllDrivers();
    List<Driver> getAllAvailableDrivers();
    Driver updateDriver(Driver driver);
    void deleteDriver(Long id);

}
