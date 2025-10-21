package org.example.taxiservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Driver extends User {
    private String licenceNumber;
    private boolean available;
    private double rating;

    @OneToOne
    private Car car;
}
