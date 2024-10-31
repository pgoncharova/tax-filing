package com.pgoncharova.taxfiling.filingrecord;

import com.pgoncharova.taxfiling.taxpayer.Taxpayer;
import com.pgoncharova.taxfiling.taxpayer.TaxpayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/filings")
public class FilingRecordController {

    private final FilingRecordService filingRecordService;
    private final TaxpayerService taxpayerService;

    @Autowired
    public FilingRecordController(FilingRecordService filingRecordService, TaxpayerService taxpayerService) {
        this.filingRecordService = filingRecordService;
        this.taxpayerService = taxpayerService;
    }

    @PostMapping("/{taxpayerId}")
    public ResponseEntity<FilingRecord> addFilingRecord(@PathVariable Long taxpayerId, @RequestBody FilingRecord filingRecord) {

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{taxpayerId}")
    public ResponseEntity<List<FilingRecord>> getFilingRecordsByTaxpayer (@PathVariable Long taxpayerId) {

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilingRecord(@PathVariable Long id) {
        filingRecordService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
