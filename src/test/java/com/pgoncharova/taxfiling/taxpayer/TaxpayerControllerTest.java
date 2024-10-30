package com.pgoncharova.taxfiling.taxpayer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class TaxpayerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    TaxpayerService taxpayerService;

    List<Taxpayer> taxpayers;

    @BeforeEach
    void setUp() {
        Taxpayer t1 = new Taxpayer();
        t1.setId(1L);
        t1.setUsername("testUser");
        t1.setEmail("testuser@example.com");
        t1.setPassword("password123");
        t1.setRoles("user");
        t1.setEnabled(true);

        Taxpayer t2 = new Taxpayer();
        t2.setId(2L);
        t2.setUsername("testUser2");
        t2.setEmail("testuser2@example.com");
        t2.setPassword("password456");
        t2.setRoles("user");
        t2.setEnabled(true);

        this.taxpayers = new ArrayList<>();
        this.taxpayers.add(t1);
        this.taxpayers.add(t2);
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * Tests the behavior of a REST API (POST) endpoint for adding a new
     * taxpayer entity. Confirms that the API returns the expected response
     * when a taxpayer is successfully added.
     *
     * @throws Exception
     */
    @Test
    void createTaxpayer() throws Exception {
        // Taxpayer data sent in the POST request body
        TaxpayerDto taxpayerDto = new TaxpayerDto(3L,
                "testUser3",
                "password789",
                "testuser3@example.com",
                "user",
                0);

        // Convert taxpayerDto to JSON string
        String json = this.objectMapper.writeValueAsString(taxpayerDto);

        // Expected data to be returned by taxpayerService
        Taxpayer savedTaxpayer = new Taxpayer();
        savedTaxpayer.setId(3L);
        savedTaxpayer.setUsername("testUser3");
        savedTaxpayer.setPassword("password789");
        savedTaxpayer.setEmail("testuser3@example.com");
        savedTaxpayer.setRoles("user");

        // Given. Mocks taxpayerService.
        // Expects any Taxpayer object as input to the save method.
        // Will return savedCustomer.
        given(this.taxpayerService.save(Mockito.any(Taxpayer.class)))
                .willReturn(savedTaxpayer);

        // When and then. Simulates HTTP POST request to /taxpayers endpoint.
        this.mockMvc.perform(post("/taxpayers")
                .contentType(MediaType.APPLICATION_JSON)    // specifies content type JSON
                        .content(json)                      // specifies JSON-encoded request body
                .accept(MediaType.APPLICATION_JSON))        // expects JSON response
                .andExpect(jsonPath("$.data.id").value(3L))
                .andExpect(jsonPath("$.data.username").value("testUser3"))
                .andExpect(jsonPath("$.data.password").value("password789"))
                .andExpect(jsonPath("$.data.email").value("testuser3@example.com"));
    }

    @Test
    void getTaxpayerByUsername() {
    }

    @Test
    void deleteTaxpayer() {
    }
}