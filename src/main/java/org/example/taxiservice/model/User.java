package org.example.taxiservice.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Long id;
    private String name;
    private String surname;
    private String phoneNumber;
    private BigDecimal balance = BigDecimal.ZERO;
    private LocalDate dateOfBirth;
    private Role role;

    public int getAge() {
        if (dateOfBirth == null) return 0;
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    public enum Role {
        DRIVER,
        PASSANGER
    }

}
