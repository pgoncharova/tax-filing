package com.pgoncharova.taxfiling.deduction;

import com.pgoncharova.taxfiling.taxpayer.Taxpayer;

import java.util.List;

public interface DeductionService {

    Deduction saveDeduction(Deduction deduction);

    List<Deduction> findDeductionByTaxpayer(Taxpayer taxpayer);

    void deleteDeduction(Long id);
}
