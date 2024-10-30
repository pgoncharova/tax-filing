package com.pgoncharova.taxfiling.deduction;

import com.pgoncharova.taxfiling.taxpayer.Taxpayer;
import com.pgoncharova.taxfiling.taxpayer.TaxpayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/deductions")
public class DeductionController {

    private final DeductionService deductionService;
    private final TaxpayerService taxpayerService;

    @Autowired
    public DeductionController(DeductionService deductionService, TaxpayerService taxpayerService) {
        this.deductionService = deductionService;
        this.taxpayerService = taxpayerService;
    }

    @PostMapping("/{taxpayerId}")
    public ResponseEntity<Deduction> addDeduction(@PathVariable Long taxpayerId, @RequestBody Deduction deduction) {
        Optional<Taxpayer> taxpayer = taxpayerService.findById(taxpayerId);
        if (taxpayer.isPresent()) {
            deduction.setTaxpayer(taxpayer.get());
            Deduction savedDeduction = deductionService.saveDeduction(deduction);
            return ResponseEntity.ok(savedDeduction);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{taxpayerId}")
    public ResponseEntity<List<Deduction>> getDeductionsByTaxpayer(@PathVariable Long taxpayerId) {
        Optional<Taxpayer> taxpayer = taxpayerService.findById(taxpayerId);
        return taxpayer.map(value -> ResponseEntity.ok(deductionService.findDeductionByTaxpayer(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeduction(@PathVariable Long id) {
        deductionService.deleteDeduction(id);
        return ResponseEntity.noContent().build();
    }

}
