package org.example.taxiservice.dto.review;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReviewRequestDTO {

    @NotNull(message = "Author ID is required")
    private Long authorId;

    @NotNull(message = "Driver ID is required")
    private Long driverId;

    @NotNull(message = "Ride ID is required")
    private Long rideId;

    @Min(1)
    @Max(5)
    private int rating;

    @NotBlank
    private String comment;
}
