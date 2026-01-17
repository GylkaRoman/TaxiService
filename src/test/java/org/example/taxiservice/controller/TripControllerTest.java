package org.example.taxiservice.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TripControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockUser(username = "passenger1", roles = "PASSENGER")
    void createTrip_success() throws Exception {

        mockMvc.perform(get("/trip")
                        .param("from", "Chisinau")
                        .param("to", "Balti")
                        .param("taxiType", "STANDARD")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
