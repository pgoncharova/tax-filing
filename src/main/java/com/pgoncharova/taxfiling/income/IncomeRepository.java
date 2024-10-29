package com.pgoncharova.taxfiling.income;

import com.pgoncharova.taxfiling.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncomeRepository extends JpaRepository<Income, Long> {

    List<Income> findByUser(User user);
}