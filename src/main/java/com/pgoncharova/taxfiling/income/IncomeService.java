package com.pgoncharova.taxfiling.income;

import com.pgoncharova.taxfiling.taxpayer.Taxpayer;

import java.util.List;

public interface IncomeService {

    Income saveIncome(Income income);

    List<Income> findIncomesByTaxpayer(Taxpayer taxpayer);

    void deleteIncome(Long id);
}
