package org.example.taxiservice.dto.driver;

import lombok.Data;

@Data
public class DriverResponseDTO {
    private Long id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String licenceNumber;
    private boolean available;
    private double rating;
    private Long carId;
}
