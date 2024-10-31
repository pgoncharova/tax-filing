package com.pgoncharova.taxfiling.filingrecord;

import com.pgoncharova.taxfiling.taxpayer.Taxpayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FilingRecordRepository extends JpaRepository<FilingRecord, Long> {

    @Query("SELECT f FROM FilingRecord f WHERE (f.taxpayer_id = :taxpayer_id)")
    List<FilingRecord> findByTaxpayerId(@Param("taxpayer_id") Long taxpayerId);

    List<FilingRecord> findByStatus(String status);
}
