package com.pgoncharova.taxfiling.taxpayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/taxpayers")
public class TaxpayerController {

    private final TaxpayerService taxpayerService;

    @Autowired
    public TaxpayerController(TaxpayerService taxpayerService) {
        this.taxpayerService = taxpayerService;
    }

    @PostMapping
    public ResponseEntity<Taxpayer> createTaxpayer(@RequestBody Taxpayer taxpayer) {
        Taxpayer savedTaxpayer = taxpayerService.saveTaxpayer(taxpayer);
        return ResponseEntity.ok(savedTaxpayer);
    }

    @GetMapping("/{username}")
    public ResponseEntity<Taxpayer> getTaxpayerByUsername(@PathVariable String username) {
        Optional<Taxpayer> taxpayer = taxpayerService.findByUsername(username);
        return taxpayer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaxpayer(@PathVariable Long id) {
        taxpayerService.deleteTaxpayer(id);
        return ResponseEntity.noContent().build();
    }
}
