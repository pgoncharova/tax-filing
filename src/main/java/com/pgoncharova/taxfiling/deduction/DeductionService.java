package com.pgoncharova.taxfiling.deduction;

import com.pgoncharova.taxfiling.user.User;

import java.util.List;

public interface DeductionService {

    Deduction saveDeduction(Deduction deduction);

    List<Deduction> findDeductionByUser(User user);

    void deleteDeduction(Long id);
}
