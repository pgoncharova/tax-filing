package com.pgoncharova.taxfiling.deduction;

import com.pgoncharova.taxfiling.filingrecord.FilingRecord;
import com.pgoncharova.taxfiling.taxpayer.Taxpayer;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Deduction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description; // Ex. "Standard Deduction", "Charitable Contributions", etc.
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "filing_record_id")
    private FilingRecord filingRecord;
}
