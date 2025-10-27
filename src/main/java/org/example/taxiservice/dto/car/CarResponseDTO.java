package org.example.taxiservice.dto.car;

import lombok.Data;

@Data
public class CarResponseDTO {
    private Long id;
    private String model;
    private String PlateNumber;
    private String taxiType;
}
