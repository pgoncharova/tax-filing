package com.pgoncharova.taxfiling.filingrecord;

import com.pgoncharova.taxfiling.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilingRecordRepository extends JpaRepository<FilingRecord, Long> {

    List<FilingRecord> findByUser(User user);

    List<FilingRecord> findByStatus(String status);
}
