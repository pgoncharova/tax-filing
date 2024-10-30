package com.pgoncharova.taxfiling.income;

import com.pgoncharova.taxfiling.taxpayer.Taxpayer;
import com.pgoncharova.taxfiling.taxpayer.TaxpayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/incomes")
public class IncomeController {

    private final IncomeService incomeService;
    private final TaxpayerService taxpayerService;

    @Autowired
    public IncomeController(IncomeService incomeService, TaxpayerService taxpayerService) {
        this.incomeService = incomeService;
        this.taxpayerService = taxpayerService;
    }

    @PostMapping("/{taxpayerId}")
    public ResponseEntity<Income> addIncome(@PathVariable Long taxpayerId, @RequestBody Income income) {

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{taxpayerId}")
    public ResponseEntity<List<Income>> getIncomesByTaxpayer(@PathVariable Long taxpayerId) {

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncome(@PathVariable Long id) {
        incomeService.deleteIncome(id);
        return ResponseEntity.noContent().build();
    }
}
