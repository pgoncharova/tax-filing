package com.pgoncharova.taxfiling.taxpayer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaxpayerRepository extends JpaRepository<Taxpayer, Long> {

    Optional<Taxpayer> findByUsername(String username);

    Optional<Taxpayer> findByEmail(String email);
}
