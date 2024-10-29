package com.pgoncharova.taxfiling.filingrecord;

import com.pgoncharova.taxfiling.user.User;

import java.util.List;

public interface FilingRecordService {

    FilingRecord saveFilingRecord(FilingRecord filingRecord);

    List<FilingRecord> findFilingRecordsByUser(User user);

    void deleteFilingRecord(Long id);
}
