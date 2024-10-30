package com.pgoncharova.taxfiling.taxpayer;

import java.util.Optional;

public interface TaxpayerService {

    Taxpayer saveTaxpayer(Taxpayer taxpayer);

    Optional<Taxpayer> findById(Long taxpayerId);

    Optional<Taxpayer> findByUsername(String username);

    Optional<Taxpayer> findByEmail(String email);

    void deleteTaxpayer(Long id);
}
