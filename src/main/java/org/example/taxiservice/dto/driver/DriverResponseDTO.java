package org.example.taxiservice.dto.driver;

import lombok.Data;
import org.example.taxiservice.model.Role;

import java.time.LocalDate;

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
    private String username;
    private Role role;
    private LocalDate dateOfBirth;
}
