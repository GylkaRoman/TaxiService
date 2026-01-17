package org.example.taxiservice.dto.car;

import lombok.Data;

@Data
public class CarResponseDTO {
    private Long id;
    private String model;
    private String plateNumber;
    private String taxiType;

    private Long driverId;
}
