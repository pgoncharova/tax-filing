package com.pgoncharova.taxfiling.filingrecord;

import com.pgoncharova.taxfiling.taxpayer.Taxpayer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilingRecordRepository extends JpaRepository<FilingRecord, Long> {

    List<FilingRecord> findByTaxpayer(Taxpayer taxpayer);

    List<FilingRecord> findByStatus(String status);
}
