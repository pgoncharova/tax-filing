package com.pgoncharova.taxfiling.filingrecord;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FilingRecordDtoToFilingRecordConverter implements Converter<FilingRecordDto, FilingRecord> {

    @Override
    public FilingRecord convert(FilingRecordDto source) {
        FilingRecord filingRecord = new FilingRecord();
        filingRecord.setId(source.id());
        filingRecord.setFilingDate(source.filingDate());
        filingRecord.setUpdatedDate(source.updatedDate());
        filingRecord.setCreatedDate(source.createdDate());
        return filingRecord;
    }
}
