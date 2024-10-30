package com.pgoncharova.taxfiling.income;

import com.pgoncharova.taxfiling.taxpayer.Taxpayer;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sourceType; // Eg. "W-2", "1099-MISC", etc.
    private Double amount;
    private Double taxWithheld;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Taxpayer taxpayer;
}
