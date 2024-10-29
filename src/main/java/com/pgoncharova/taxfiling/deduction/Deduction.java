package com.pgoncharova.taxfiling.deduction;

import com.pgoncharova.taxfiling.user.User;
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
    @JoinColumn(name = "user_id")
    private User user;
}
