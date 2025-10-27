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
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String model;

    private String plateNumber;

    @Enumerated(EnumType.STRING)
    private TaxiType taxiType;

    @OneToOne(mappedBy = "car")
    private Driver driver;
}
