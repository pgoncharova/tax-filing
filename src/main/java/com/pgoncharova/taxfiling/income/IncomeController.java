package com.pgoncharova.taxfiling.income;

import com.pgoncharova.taxfiling.user.User;
import com.pgoncharova.taxfiling.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/incomes")
public class IncomeController {

    private final IncomeService incomeService;
    private final UserService userService;

    @Autowired
    public IncomeController(IncomeService incomeService, UserService userService) {
        this.incomeService = incomeService;
        this.userService = userService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Income> addIncome(@PathVariable Long userId, @RequestBody Income income) {
        Optional<User> user = userService.findById(userId);
        if (user.isPresent()) {
            income.setUser(user.get());
            Income savedIncome = incomeService.saveIncome(income);
            return ResponseEntity.ok(savedIncome);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Income>> getIncomesByUser(@PathVariable Long userId) {
        Optional<User> user = userService.findById(userId);
        return user.map(value -> ResponseEntity.ok(incomeService.findIncomesByUser(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncome(@PathVariable Long id) {
        incomeService.deleteIncome(id);
        return ResponseEntity.noContent().build();
    }
}
