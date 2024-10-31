package com.pgoncharova.taxfiling.filingrecord;

import com.pgoncharova.taxfiling.deduction.Deduction;
import com.pgoncharova.taxfiling.income.Income;
import com.pgoncharova.taxfiling.taxpayer.Taxpayer;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class FilingRecord implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate filingDate;
    private LocalDate updatedDate;
    private LocalDate createdDate;
    private String status; // Ex. "Single", "Married Filing Jointly", etc.
    private Double totalIncome;
    private Double totalDeductions;
    private Double totalTaxCredits; // TODO: research how this works.
    private Double taxOwedOrRefund;

    @ManyToOne
    @JoinColumn(name = "taxpayer_id")
    private Taxpayer taxpayer;

    @OneToMany(mappedBy = "filing_record", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Income> incomes = new ArrayList<>();

    @OneToMany(mappedBy = "filing_record", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Deduction> deductions = new ArrayList<>();

}
