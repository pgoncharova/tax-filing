package com.pgoncharova.taxfiling.income;

import com.pgoncharova.taxfiling.user.User;

import java.util.List;

public interface IncomeService {

    Income saveIncome(Income income);

    List<Income> findIncomesByUser(User user);

    void deleteIncome(Long id);
}
