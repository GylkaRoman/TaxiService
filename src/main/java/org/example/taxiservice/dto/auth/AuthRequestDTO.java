package org.example.taxiservice.dto.auth;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class AuthRequestDTO {
    private String username;
    private String password;
    private String name;
    private String surname;
    private String phoneNumber;
    private BigDecimal balance;
    private LocalDate dateOfBirth;
}
