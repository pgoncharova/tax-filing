package com.pgoncharova.taxfiling.income;

import com.pgoncharova.taxfiling.taxpayer.Taxpayer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncomeRepository extends JpaRepository<Income, Long> {

    List<Income> findByTaxpayer(Taxpayer taxpayer);
}
