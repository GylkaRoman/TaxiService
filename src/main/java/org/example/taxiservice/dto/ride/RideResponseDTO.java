package org.example.taxiservice.dto.ride;

import lombok.Data;

@Data
public class RideResponseDTO {
    private Long id;
    private Long passengerId;
    private Long driverId;
    private Long carId;
    private String startLocation;
    private String endLocation;
    private double price;
}
