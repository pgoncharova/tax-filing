package com.pgoncharova.taxfiling.filingrecord;

import com.pgoncharova.taxfiling.user.User;
import com.pgoncharova.taxfiling.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/filings")
public class FilingRecordController {

    private final FilingRecordServiceImpl filingRecordService;
    private final UserService userService;

    @Autowired
    public FilingRecordController(FilingRecordServiceImpl filingRecordService, UserService userService) {
        this.filingRecordService = filingRecordService;
        this.userService = userService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<FilingRecord> addFilingRecord(@PathVariable Long userId, @RequestBody FilingRecord filingRecord) {
        Optional<User> user = userService.findById(userId);
        if (user.isPresent()) {
            filingRecord.setUser(user.get());
            FilingRecord savedRecord = filingRecordService.saveFilingRecord(filingRecord);
            return ResponseEntity.ok(savedRecord);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<FilingRecord>> getFilingRecordsByUser (@PathVariable Long userId) {
        Optional<User> user = userService.findById(userId);
        return user.map(value -> ResponseEntity.ok(filingRecordService.findFilingRecordsByUser(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilingRecord(@PathVariable Long id) {
        filingRecordService.deleteFilingRecord(id);
        return ResponseEntity.noContent().build();
    }

}
