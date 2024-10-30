package com.pgoncharova.taxfiling.filingrecord;

import com.pgoncharova.taxfiling.taxpayer.Taxpayer;

import java.util.List;

public interface FilingRecordService {

    FilingRecord saveFilingRecord(FilingRecord filingRecord);

    List<FilingRecord> findFilingRecordsByTaxpayer(Taxpayer taxpayer);

    void deleteFilingRecord(Long id);
}
