package com.pgoncharova.taxfiling.filingrecord;

import com.pgoncharova.taxfiling.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class FilingRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate filingDate;
    private String status; // Ex. "Single", "Married Filing Jointly", etc.
    private Double totalIncome;
    private Double totalDeductions;
    private Double taxOwedOrRefund;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
