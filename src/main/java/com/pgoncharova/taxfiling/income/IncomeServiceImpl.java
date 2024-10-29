package com.pgoncharova.taxfiling.income;

import com.pgoncharova.taxfiling.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeServiceImpl implements IncomeService {

    private final IncomeRepository incomeRepository;

    @Autowired
    public IncomeServiceImpl(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    @Override
    public Income saveIncome(Income income) {
        return incomeRepository.save(income);
    }

    @Override
    public List<Income> findIncomesByUser(User user) {
        return incomeRepository.findByUser(user);
    }

    @Override
    public void deleteIncome(Long id) {
        incomeRepository.deleteById(id);
    }
}
