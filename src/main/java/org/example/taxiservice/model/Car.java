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
public class Car {
    private Long id;

    private String model;
    private String carPlate;
    private TaxiType taxiType;

    private Driver driver;
}
