package com.pgoncharova.taxfiling.filingrecord;

import com.pgoncharova.taxfiling.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilingRecordServiceImpl implements FilingRecordService {

    private final FilingRecordRepository filingRecordRepository;

    @Autowired
    public FilingRecordServiceImpl(FilingRecordRepository filingRecordRepository) {
        this.filingRecordRepository = filingRecordRepository;
    }

    @Override
    public FilingRecord saveFilingRecord(FilingRecord filingRecord) {
        return filingRecordRepository.save(filingRecord);
    }

    @Override
    public List<FilingRecord> findFilingRecordsByUser(User user) {
        return filingRecordRepository.findByUser(user);
    }

    @Override
    public void deleteFilingRecord(Long id) {
        filingRecordRepository.deleteById(id);
    }
}
