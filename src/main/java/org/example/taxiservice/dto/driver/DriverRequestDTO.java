package org.example.taxiservice.dto.driver;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class DriverRequestDTO {
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Surname cannot be empty")
    private String surname;

    @Pattern(regexp = "\\+?[0-9]{10,15}", message = "Invalid phone number")
    private String phoneNumber;

    @NotBlank(message = "Licence number cannot be empty")
    private String licenceNumber;

    private boolean available;

    @PositiveOrZero(message = "Rating must be zero or positive")
    private double rating;

    @NotNull(message = "Car ID is required")
    private Long carId;
}
