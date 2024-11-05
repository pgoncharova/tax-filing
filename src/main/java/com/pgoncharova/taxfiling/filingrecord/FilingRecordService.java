package com.pgoncharova.taxfiling.filingrecord;

import com.pgoncharova.taxfiling.system.exception.ObjectNotFoundException;
import com.pgoncharova.taxfiling.taxpayer.Taxpayer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class FilingRecordService {

    private final FilingRecordRepository filingRecordRepository;

    public FilingRecordService(FilingRecordRepository filingRecordRepository) {
        this.filingRecordRepository = filingRecordRepository;
    }

    public FilingRecord save(FilingRecord newFilingRecord) {
        return this.filingRecordRepository.save(newFilingRecord);
    }

    public List<FilingRecord> findAll() {
        return this.filingRecordRepository.findAll();
    }

    public List<FilingRecord> findByTaxpayerId(Long taxpayerId) {
        return this.filingRecordRepository.findByTaxpayerId(taxpayerId);
    }

    public FilingRecord findById(Long filingRecordId) {
        return this.filingRecordRepository.findById(filingRecordId)
                .orElseThrow(() -> new ObjectNotFoundException("filing record", filingRecordId));
    }

    public FilingRecord update(Long filingRecordId, FilingRecord update) {
        FilingRecord oldFilingRecord = this.filingRecordRepository.findById(filingRecordId)
                .orElseThrow(() -> new ObjectNotFoundException("filing record", filingRecordId));
        oldFilingRecord.setFilingDate(update.getFilingDate());
        oldFilingRecord.setUpdatedDate(update.getUpdatedDate());
        oldFilingRecord.setCreatedDate(update.getCreatedDate());
        return this.filingRecordRepository.save(oldFilingRecord);
    }

    public void delete(Long filingRecordId) {
        this.filingRecordRepository.findById(filingRecordId)
                .orElseThrow(() -> new ObjectNotFoundException("filing record", filingRecordId));
        this.filingRecordRepository.deleteById(filingRecordId);
    }
}
