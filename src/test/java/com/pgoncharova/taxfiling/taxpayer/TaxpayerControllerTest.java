package com.pgoncharova.taxfiling.taxpayer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pgoncharova.taxfiling.system.StatusCode;
import com.pgoncharova.taxfiling.system.exception.ObjectNotFoundException;
import org.hamcrest.Matchers;
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
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
        t1.setSsn("123-45-6789");
        t1.setLastName("Doe");

        Taxpayer t2 = new Taxpayer();
        t2.setId(2L);
        t2.setUsername("testUser2");
        t2.setEmail("testuser2@example.com");
        t2.setPassword("password456");
        t2.setRoles("user");
        t2.setEnabled(true);
        t2.setSsn("987-65-4321");
        t2.setLastName("Doe");

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
    void addTaxpayer() throws Exception {
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
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Add Success"))
                .andExpect(jsonPath("$.data.id").value(3L))
                .andExpect(jsonPath("$.data.username").value("testUser3"))
                .andExpect(jsonPath("$.data.password").value("password789"))
                .andExpect(jsonPath("$.data.email").value("testuser3@example.com"));
    }

    @Test
    void findAllTaxpayers() throws Exception {
        // Given
        given(this.taxpayerService.findAll()).willReturn(this.taxpayers);

        // When and then
        this.mockMvc.perform(get("/taxpayers").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Find All Success"))
                .andExpect(jsonPath("$.data", Matchers.hasSize(this.taxpayers.size())))
                .andExpect(jsonPath("$.data[0].id").value(1L))
                .andExpect(jsonPath("$.data[0].username").value("testUser"))
                .andExpect(jsonPath("$.data[1].id").value(2L))
                .andExpect(jsonPath("$.data[1].username").value("testUser2"));
    }

    @Test
    void searchTaxpayers() throws Exception {
        // Given
        given(this.taxpayerService.findByFilters("123-45-6789", null))
                .willReturn(Arrays.asList(this.taxpayers.get(0)));

        // When and then
        this.mockMvc.perform(get("/taxpayers/search").accept(MediaType.APPLICATION_JSON)
                .param("ssn", "123-45-6789"))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Search Success"))
                .andExpect(jsonPath("$.data", Matchers.hasSize(1)))
                .andExpect(jsonPath("$.data[0].id").value(1L))
                .andExpect(jsonPath("$.data[0].username").value("testUser"));
    }

    @Test
    void findTaxpayerByIdSuccess() throws Exception {
        // Given
        given(this.taxpayerService.findById(2L)).willReturn(this.taxpayers.get(1));

        // When and then
        this.mockMvc.perform(get("/taxpayers/2").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Find One Success"))
                .andExpect(jsonPath("$.data.id").value(2L))
                .andExpect(jsonPath("$.data.username").value("testUser2"));
    }

    @Test
    void findTaxpayerByIdNotFound() throws Exception {
        // Given
        given(this.taxpayerService.findById(5L)).willThrow(new ObjectNotFoundException("taxpayer", 5L));

        // When and then
        this.mockMvc.perform(get("/taxpayers/5").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(false))
                .andExpect(jsonPath("$.code").value(StatusCode.NOT_FOUND))
                .andExpect(jsonPath("$.message").value("Could not find taxpayer with id 5"))
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void updateTaxpayerSuccess() throws Exception {
        // Given
        TaxpayerDto taxpayerDto = new TaxpayerDto(3L, "testUser3",
                "password789", "testuser3@example.com",
                "user", 0);

        Taxpayer updatedTaxpayer = new Taxpayer();
        updatedTaxpayer.setId(3L);
        updatedTaxpayer.setUsername("testUser3 - update");
        updatedTaxpayer.setPassword("password789");
        updatedTaxpayer.setEmail("testuser3@example.com");
        updatedTaxpayer.setRoles("user");

        String json = this.objectMapper.writeValueAsString(taxpayerDto);

        given(this.taxpayerService.update(eq(3L), Mockito.any(Taxpayer.class)))
                .willReturn(updatedTaxpayer);

        // When and then
        this.mockMvc.perform(put("/taxpayers/3").contentType(MediaType.APPLICATION_JSON)
                .content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Update Success"))
                .andExpect(jsonPath("$.data.id").value(3L))
                .andExpect(jsonPath("$.data.username").value("testUser3 - update"))
                .andExpect(jsonPath("$.data.password").value("password789"))
                .andExpect(jsonPath("$.data.email").value("testuser3@example.com"));
    }

    @Test
    void updateTaxpayerNotFound() throws Exception {
        // Given
        given(this.taxpayerService.update(eq(5L), Mockito.any(Taxpayer.class)))
                .willThrow(new ObjectNotFoundException("taxpayer", 5L));

        TaxpayerDto taxpayerDto = new TaxpayerDto(5L, "testUser5",
                "password456", "testuser5@example.com",
                "user", 0);

        String json = this.objectMapper.writeValueAsString(taxpayerDto);

        // When and then
        this.mockMvc.perform(put("/taxpayers/5").contentType(MediaType.APPLICATION_JSON)
                .content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(false))
                .andExpect(jsonPath("$.code").value(StatusCode.NOT_FOUND))
                .andExpect(jsonPath("$.message").value("Could not find taxpayer with id 5"))
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void deleteTaxpayerSuccess() throws Exception {
        // Given
        doNothing().when(this.taxpayerService).delete(2L);

        // When and then
        this.mockMvc.perform(delete("/taxpayers/2").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(StatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Delete Success"));
    }

    @Test
    void deleteTaxpayerNotFound() throws Exception {
        // Given
        doThrow(new ObjectNotFoundException("taxpayer", 5L)).when(this.taxpayerService).delete(5L);

        // When and then
        this.mockMvc.perform(delete("/taxpayers/5").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(false))
                .andExpect(jsonPath("$.code").value(StatusCode.NOT_FOUND))
                .andExpect(jsonPath("$.message").value("Could not find taxpayer with id 5"))
                .andExpect(jsonPath("$.data").isEmpty());
    }
}