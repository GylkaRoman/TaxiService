package org.example.taxiservice.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Driver extends User {
    private String licenceNumber;
    private boolean available;
    private double rating;

    private Car car;
    private Review review;
}
