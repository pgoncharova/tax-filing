package com.pgoncharova.taxfiling.deduction;

import com.pgoncharova.taxfiling.taxpayer.Taxpayer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DeductionRepository extends JpaRepository<Deduction, Long> {

    List<Deduction> findByTaxpayer(Taxpayer taxpayer);
}
