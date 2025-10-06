package org.example.taxiservice.dto.ride;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class RideRequestDTO {
    @NotNull(message = "Passenger ID is required")
    private Long passengerId;

    @NotNull(message = "Driver ID is required")
    private Long driverId;

    @NotNull(message = "Car ID is required")
    private Long carId;

    @NotBlank(message = "Start location cannot be empty")
    private String startLocation;

    @NotBlank(message = "End location cannot be empty")
    private String endLocation;

    @Positive(message = "Price must be positive")
    private double price;
}
