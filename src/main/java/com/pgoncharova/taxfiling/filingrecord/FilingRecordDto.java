package com.pgoncharova.taxfiling.filingrecord;

import java.time.LocalDate;

public record FilingRecordDto (Long id,
                               LocalDate filingDate,
                               LocalDate updatedDate,
                               LocalDate createdDate) {
}
