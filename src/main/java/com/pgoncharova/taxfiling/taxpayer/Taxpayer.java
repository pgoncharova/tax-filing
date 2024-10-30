package com.pgoncharova.taxfiling.taxpayer;

import com.pgoncharova.taxfiling.deduction.Deduction;
import com.pgoncharova.taxfiling.filingrecord.FilingRecord;
import com.pgoncharova.taxfiling.income.Income;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Taxpayer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;
    private String roles; // Could be "user" or "admin"
    private boolean enabled;

    private String ssn;
    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "taxpayer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FilingRecord> filingRecords = new ArrayList<>();

    public Integer getNumberOfFilingRecords() {
        return this.filingRecords.size();
    }
}
