package com.pgoncharova.taxfiling.deduction;

import com.pgoncharova.taxfiling.user.User;
import com.pgoncharova.taxfiling.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/deductions")
public class DeductionController {

    private final DeductionService deductionService;
    private final UserService userService;

    @Autowired
    public DeductionController(DeductionService deductionService, UserService userService) {
        this.deductionService = deductionService;
        this.userService = userService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Deduction> addDeduction(@PathVariable Long userId, @RequestBody Deduction deduction) {
        Optional<User> user = userService.findById(userId);
        if (user.isPresent()) {
            deduction.setUser(user.get());
            Deduction savedDeduction = deductionService.saveDeduction(deduction);
            return ResponseEntity.ok(savedDeduction);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Deduction>> getDeductionsByUser(@PathVariable Long userId) {
        Optional<User> user = userService.findById(userId);
        return user.map(value -> ResponseEntity.ok(deductionService.findDeductionByUser(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeduction(@PathVariable Long id) {
        deductionService.deleteDeduction(id);
        return ResponseEntity.noContent().build();
    }

}
