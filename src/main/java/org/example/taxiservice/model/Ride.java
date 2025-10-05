package org.example.taxiservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User passenger;
    @ManyToOne
    private Driver driver;
    @ManyToOne
    private Car car;

    private String startLocation;
    private String endLocation;

    private double price;
}
