package com.pgoncharova.taxfiling.filingrecord;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FilingRecordServiceTest {

    @Mock
    FilingRecordRepository filingRecordRepository;

    @InjectMocks
    FilingRecordService filingRecordService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() {
        // Given
        FilingRecord newFilingRecord = new FilingRecord();
        newFilingRecord.setFilingDate(LocalDate.now());

        given(this.filingRecordRepository.save(newFilingRecord))
                .willReturn(newFilingRecord);

        // When
        FilingRecord returnedFilingRecord = this.filingRecordService.save(newFilingRecord);

        // Then
        assertThat(returnedFilingRecord.getFilingDate()).isEqualTo(newFilingRecord.getFilingDate());
        verify(this.filingRecordRepository, times(1)).save(newFilingRecord);
    }

    @Test
    void findAll() {
    }

    @Test
    void findByTaxpayerId() {
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
}