package com.pgoncharova.taxfiling.taxpayer;

import com.pgoncharova.taxfiling.deduction.Deduction;
import com.pgoncharova.taxfiling.filingrecord.FilingRecord;
import com.pgoncharova.taxfiling.income.Income;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Taxpayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;
    private String role; // Could be "user" or "admin"

    @OneToMany(mappedBy = "taxpayer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FilingRecord> filingRecords = new ArrayList<>();

    public Integer getNumberOfFilingRecords() {
        return this.filingRecords.size();
    }
}
