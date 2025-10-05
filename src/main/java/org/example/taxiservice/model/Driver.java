package org.example.taxiservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
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

    @OneToOne
    private Car car;
}
