package com.pgoncharova.taxfiling.taxpayer;

import com.pgoncharova.taxfiling.system.exception.ObjectNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

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
        // Given.
        given(this.taxpayerRepository.findAll()).willReturn(this.taxpayers);

        // When.
        List<Taxpayer> actualTaxpayers = this.taxpayerService.findAll();

        // Then.
        assertThat(actualTaxpayers.size()).isEqualTo(this.taxpayers.size());
        verify(this.taxpayerRepository, times(1)).findAll();
    }

    @Test
    void findByIdSuccess() {
        // Given.
        Taxpayer taxpayer = new Taxpayer();
        taxpayer.setId(1L);
        taxpayer.setUsername("testUser");
        taxpayer.setEmail("testuser@example.com");
        taxpayer.setPassword("password123");
        taxpayer.setRoles("user");
        taxpayer.setEnabled(true);

        given(this.taxpayerRepository.findById(1L)).willReturn(Optional.of(taxpayer));

        // When.
        Taxpayer returnedTaxpayer = this.taxpayerService.findById(1L);

        // Then.
        assertThat(returnedTaxpayer.getId()).isEqualTo(taxpayer.getId());
        assertThat(returnedTaxpayer.getUsername()).isEqualTo(taxpayer.getUsername());
        assertThat(returnedTaxpayer.getPassword()).isEqualTo(taxpayer.getPassword());
        assertThat(returnedTaxpayer.getEmail()).isEqualTo(taxpayer.getEmail());
        assertThat(returnedTaxpayer.getRoles()).isEqualTo(taxpayer.getRoles());
        assertThat(returnedTaxpayer.isEnabled()).isEqualTo(taxpayer.isEnabled());
        verify(this.taxpayerRepository, times(1)).findById(1L);
    }

    @Test
    void findByIdNotFound() {
        // Given
        given(this.taxpayerRepository.findById(Mockito.any(Long.class)))
                .willReturn(Optional.empty());

        // When
        Throwable thrown = catchThrowable(() -> {
            Taxpayer returnedTaxpayer = this.taxpayerService.findById(1L);
        });

        // Then
        assertThat(thrown)
                .isInstanceOf(ObjectNotFoundException.class)
                .hasMessage("Could not find taxpayer with id 1");
        verify(this.taxpayerRepository, times(1)).findById(Mockito.any(Long.class));
    }

    @Test
    void updateSuccess() {
        // Given
        Taxpayer oldTaxpayer = new Taxpayer();
        oldTaxpayer.setId(1L);
        oldTaxpayer.setUsername("testUser");
        oldTaxpayer.setEmail("testuser@example.com");
        oldTaxpayer.setPassword("password123");
        oldTaxpayer.setRoles("user");
        oldTaxpayer.setEnabled(true);

        Taxpayer update = new Taxpayer();
        update.setUsername("testUser - update");
        update.setEmail("testuser@example.com");
        update.setPassword("password123");
        update.setRoles("user");
        update.setEnabled(true);

        given(this.taxpayerRepository.findById(1L))
                .willReturn(Optional.of(oldTaxpayer));
        given(this.taxpayerRepository.save(oldTaxpayer))
                .willReturn(oldTaxpayer);

        // When
        Taxpayer updatedTaxpayer = this.taxpayerService.update(1L, update);

        // Then
        assertThat(updatedTaxpayer.getId()).isEqualTo(1L);
        assertThat(updatedTaxpayer.getUsername()).isEqualTo(update.getUsername());
        verify(this.taxpayerRepository, times(1)).findById(1L);
        verify(this.taxpayerRepository, times(1)).save(oldTaxpayer);
    }

    @Test
    void updateNotFound() {
        // Given
        Taxpayer update = new Taxpayer();
        update.setUsername("testUser - update");
        update.setEmail("testuser@example.com");
        update.setPassword("password123");
        update.setRoles("user");
        update.setEnabled(true);

        given(this.taxpayerRepository.findById(1L)).willReturn(Optional.empty());

        // When
        Throwable thrown = assertThrows(ObjectNotFoundException.class, () -> {
            this.taxpayerService.update(1L, update);
        });

        // Then
        assertThat(thrown)
                .isInstanceOf(ObjectNotFoundException.class)
                .hasMessage("Could not find taxpayer with id 1");
        verify(this.taxpayerRepository, times(1)).findById(1L);
    }

    @Test
    void deleteSuccess() {
        // Given
        Taxpayer taxpayer = new Taxpayer();
        taxpayer.setUsername("testUser");
        taxpayer.setEmail("testuser@example.com");
        taxpayer.setPassword("password123");
        taxpayer.setRoles("user");
        taxpayer.setEnabled(true);

        given(this.taxpayerRepository.findById(1L)).willReturn(Optional.of(taxpayer));
        doNothing().when(this.taxpayerRepository).deleteById(1L);

        // When
        this.taxpayerService.delete(1L);

        // Then
        verify(this.taxpayerRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteNotFound() {
        // Given
        // When
        // Then
    }

    @Test
    void loadUserByUsername() {
        // Given
        // When
        // Then
    }

    @Test
    void loadUserByEmail() {
        // Given
        // When
        // Then
    }
}