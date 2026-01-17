package org.example.taxiservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.taxiservice.dto.auth.AuthRequestDTO;
import org.example.taxiservice.dto.driver.DriverRequestDTO;
import org.example.taxiservice.repository.DriverRepository;
import org.example.taxiservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Test
    void loginPassenger_success() throws Exception {
        AuthRequestDTO passenger = new AuthRequestDTO();
        passenger.setUsername("11ikbtgumjynhbtgrvfecxwdcefvrgt3456");
        passenger.setPassword("123456");
        passenger.setName("Ivan");
        passenger.setSurname("Ivanov");
        passenger.setPhoneNumber("+37369123456");
        passenger.setBalance(BigDecimal.valueOf(100));
        passenger.setDateOfBirth(LocalDate.of(2000, 1, 1));

        mockMvc.perform(post("/auth/register/passenger")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(passenger)))
                .andExpect(status().isOk());

        AuthRequestDTO loginDto = new AuthRequestDTO();
        loginDto.setUsername("11ikbtgumjynhbtgrvfecxwdcefvrgt3456");
        loginDto.setPassword("123456");

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDto)))
                .andExpect(status().isOk());
    }

    @Test
    void loginDriver_success() throws Exception {
        DriverRequestDTO driver = new DriverRequestDTO();
        driver.setUsername("22jyrnhbgfvgbhnjmhngbgthnbgfdfc43333334");
        driver.setPassword("123456");
        driver.setName("Petr");
        driver.setSurname("Petrov");
        driver.setPhoneNumber("+37369123457");
        driver.setDateOfBirth(LocalDate.of(1995, 5, 5));

        mockMvc.perform(post("/auth/register/driver")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(driver)))
                .andExpect(status().isOk());

        AuthRequestDTO loginDto = new AuthRequestDTO();
        loginDto.setUsername("22jyrnhbgfvgbhnjmhngbgthnbgfdfc43333334");
        loginDto.setPassword("123456");

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDto)))
                .andExpect(status().isOk());
    }
}
