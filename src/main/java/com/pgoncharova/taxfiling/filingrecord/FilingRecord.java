package com.pgoncharova.taxfiling.filingrecord;

import com.pgoncharova.taxfiling.deduction.Deduction;
import com.pgoncharova.taxfiling.filingrecord.address.Address;
import com.pgoncharova.taxfiling.filingrecord.dependent.Dependent;
import com.pgoncharova.taxfiling.filingrecord.filingstatus.FilingStatus;
import com.pgoncharova.taxfiling.filingrecord.standarddeduction.StandardDeduction;
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

    private String firstName;
    private String middleInitial;
    private String lastName;
    private String ssn;
    private FilingStatus filingStatus; // Ex. "Single", "Married Filing Jointly", etc.
    private Address address;
    private boolean hasDigitalAssets;
    private StandardDeduction standardDeduction;
    private List<Dependent> dependents;

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
