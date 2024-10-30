package com.pgoncharova.taxfiling.taxpayer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TaxpayerServiceTest {

    @Mock
    TaxpayerRepository taxpayerRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
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

        Taxpayer t2 = new Taxpayer();
        t2.setId(2L);
        t2.setUsername("testUser2");
        t2.setEmail("testuser2@example.com");
        t2.setPassword("password456");
        t2.setRoles("user");

        this.taxpayers = new ArrayList<>();
        this.taxpayers.add(t1);
        this.taxpayers.add(t2);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() {
        // Given.
        Taxpayer newTaxpayer = new Taxpayer();
        newTaxpayer.setUsername("testUser3");
        newTaxpayer.setPassword("password789");
        newTaxpayer.setEmail("testuser3@example.com");
        newTaxpayer.setRoles("user");
        newTaxpayer.setEnabled(true);

        given(this.passwordEncoder.encode(newTaxpayer.getPassword()))
                .willReturn("Encoded Password");
        given(this.taxpayerRepository.save(newTaxpayer))
                .willReturn(newTaxpayer);

        // When.
        Taxpayer returnedTaxpayer = this.taxpayerService.save(newTaxpayer);

        // Then.
        assertThat(returnedTaxpayer.getUsername()).isEqualTo(newTaxpayer.getUsername());
        assertThat(returnedTaxpayer.getPassword()).isEqualTo(newTaxpayer.getPassword());
        assertThat(returnedTaxpayer.getEmail()).isEqualTo(newTaxpayer.getEmail());
        assertThat(returnedTaxpayer.getRoles()).isEqualTo(newTaxpayer.getRoles());
        assertThat(returnedTaxpayer.isEnabled()).isEqualTo(newTaxpayer.isEnabled());
        verify(this.taxpayerRepository, times(1)).save(newTaxpayer);
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void loadUserByUsername() {
    }

    @Test
    void loadUserByEmail() {
    }
}