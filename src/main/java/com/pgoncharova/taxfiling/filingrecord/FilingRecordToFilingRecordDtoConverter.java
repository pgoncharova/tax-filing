package com.pgoncharova.taxfiling.filingrecord;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FilingRecordToFilingRecordDtoConverter implements Converter<FilingRecord, FilingRecordDto> {

    @Override
    public FilingRecordDto convert(FilingRecord source) {
        FilingRecordDto filingRecordDto = new FilingRecordDto(source.getId(),
                source.getFilingDate(),
                source.getUpdatedDate(),
                source.getCreatedDate());
        return filingRecordDto;
    }
}
