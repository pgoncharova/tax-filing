package com.pgoncharova.taxfiling.taxpayer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaxpayerRepository extends JpaRepository<Taxpayer, Long> {

    Optional<Taxpayer> findByUsername(String username);

    Optional<Taxpayer> findByEmail(String email);

    @Query("SELECT t FROM Taxpayer t WHERE " +
            "(:ssn IS NULL OR t.ssn = :ssn) AND " +
            "(:lastName IS NULL OR t.lastName LIKE %:lastName%)")
    List<Taxpayer> findByFilters(@Param("ssn") String ssn, @Param("lastName") String lastName);
}
