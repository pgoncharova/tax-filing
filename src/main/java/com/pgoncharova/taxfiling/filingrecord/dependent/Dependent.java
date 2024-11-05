package com.pgoncharova.taxfiling.filingrecord.dependent;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class Dependent implements Serializable {

    private String firstName;
    private String lastName;
    private String ssn;
    private String relationship;
    private boolean qualifiesForChildTaxCredit;
    private boolean qualifiesForCreditForOtherDependents;
}
