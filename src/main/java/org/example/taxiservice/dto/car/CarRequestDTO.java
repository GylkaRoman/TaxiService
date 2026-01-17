package org.example.taxiservice.dto.car;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CarRequestDTO {
    @NotBlank(message = "Model cannot be empty")
    private String model;

    @NotBlank(message = "Car plate cannot be empty")
    private String plateNumber;

    @NotNull(message = "Taxi type is required")
    private String taxiType;

    private Long driverId;
}
