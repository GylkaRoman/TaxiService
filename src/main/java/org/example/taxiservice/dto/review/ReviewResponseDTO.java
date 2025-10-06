package org.example.taxiservice.dto.review;

import lombok.Data;

@Data
public class ReviewResponseDTO {
    private Long id;
    private Long authorId;
    private Long driverId;
    private Long rideId;
    private int rating;
    private String comment;
}
