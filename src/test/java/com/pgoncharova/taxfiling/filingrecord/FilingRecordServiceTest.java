package com.pgoncharova.taxfiling.filingrecord;

import com.pgoncharova.taxfiling.taxpayer.Taxpayer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FilingRecordServiceTest {

    @Mock
    FilingRecordRepository filingRecordRepository;

    @InjectMocks
    FilingRecordService filingRecordService;

    List<FilingRecord> filingRecords;

    @BeforeEach
    void setUp() {
        Taxpayer t1 = new Taxpayer();
        t1.setId(1L);

        Taxpayer t2 = new Taxpayer();
        t2.setId(2L);

        FilingRecord f1 = new FilingRecord();
        f1.setId(1L);
        f1.setFilingDate(LocalDate.of(2024, 10, 27));
        f1.setTaxpayer(t1);

        FilingRecord f2 = new FilingRecord();
        f2.setId(2L);
        f2.setFilingDate(LocalDate.of(2024, 11, 5));
        f2.setTaxpayer(t2);

        this.filingRecords = new ArrayList<>();
        this.filingRecords.add(f1);
        this.filingRecords.add(f2);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() {
        // Given
        FilingRecord newFilingRecord = new FilingRecord();
        newFilingRecord.setFilingDate(LocalDate.now());
        newFilingRecord.setId(3L);

        given(this.filingRecordRepository.save(newFilingRecord))
                .willReturn(newFilingRecord);

        // When
        FilingRecord returnedFilingRecord = this.filingRecordService.save(newFilingRecord);

        // Then
        assertThat(returnedFilingRecord.getFilingDate()).isEqualTo(newFilingRecord.getFilingDate());
        assertThat(returnedFilingRecord.getId()).isEqualTo(newFilingRecord.getId());
        verify(this.filingRecordRepository, times(1)).save(newFilingRecord);
    }

    @Test
    void findAll() {
        // Given
        given(this.filingRecordRepository.findAll()).willReturn(this.filingRecords);

        // When
        List<FilingRecord> actualFilingRecords = this.filingRecordService.findAll();

        // Then
        assertThat(actualFilingRecords.size()).isEqualTo(this.filingRecords.size());
        verify(this.filingRecordRepository, times(1)).findAll();
    }

    @Test
    void findByTaxpayerId() {
        Taxpayer taxpayer = new Taxpayer();
        taxpayer.setId(1L);

        FilingRecord filingRecord = new FilingRecord();
        filingRecord.setId(3L);
        filingRecord.setFilingDate(LocalDate.now());

        when(this.filingRecordRepository.findByTaxpayerId(1L))
                .thenReturn(Arrays.asList(filingRecord));

        List<FilingRecord> result = this.filingRecordService.findByTaxpayerId(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(filingRecord.getId(), result.get(0).getId());
        assertEquals(filingRecord.getFilingDate(), result.get(0).getFilingDate());
    }

    @Test
    void findById() {
        // Given
        FilingRecord filingRecord = new FilingRecord();
        filingRecord.setId(3L);
        filingRecord.setFilingDate(LocalDate.now());

        given(this.filingRecordRepository.findById(1L)).willReturn(Optional.of(filingRecord));

        // When
        FilingRecord returnedFilingRecord = this.filingRecordService.findById(1L);

        // Then
        assertThat(returnedFilingRecord.getId()).isEqualTo(filingRecord.getId());
        assertThat(returnedFilingRecord.getFilingDate()).isEqualTo(filingRecord.getFilingDate());
        verify(this.filingRecordRepository, times(1)).findById(1L);
    }

    @Test
    void update() {
        // Given
        FilingRecord oldFilingRecord = new FilingRecord();
        oldFilingRecord.setId(3L);
        oldFilingRecord.setUpdatedDate(LocalDate.of(2024, 3, 15));

        FilingRecord update = new FilingRecord();
        update.setUpdatedDate(LocalDate.now());

        given(this.filingRecordRepository.findById(3L))
                .willReturn(Optional.of(oldFilingRecord));
        given(this.filingRecordRepository.save(oldFilingRecord))
                .willReturn(oldFilingRecord);

        // When
        FilingRecord updatedFilingRecord = this.filingRecordService.update(3L, update);

        // Then
        assertThat(updatedFilingRecord.getId()).isEqualTo(3L);
        assertThat(updatedFilingRecord.getUpdatedDate()).isEqualTo(update.getUpdatedDate());
        verify(this.filingRecordRepository, times(1)).findById(3L);
        verify(this.filingRecordRepository, times(1)).save(oldFilingRecord);
    }

    @Test
    void delete() {
        // Given
        FilingRecord filingRecord = new FilingRecord();
        filingRecord.setId(3L);
        filingRecord.setUpdatedDate(LocalDate.now());

        given(this.filingRecordRepository.findById(3L))
                .willReturn(Optional.of(filingRecord));
        doNothing().when(this.filingRecordRepository).deleteById(3L);

        // When
        this.filingRecordService.delete(3L);

        // Then
        verify(this.filingRecordRepository, times(1)).deleteById(3L);
    }
}