package org.example.taxiservice.dto.user;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class UserResponseDTO {
    private Long id;
    private String name;
    private String surname;
    private String phoneNumber;
    private BigDecimal balance;
    private LocalDate dateOfBirth;
    private String role;
    private int age;
}
