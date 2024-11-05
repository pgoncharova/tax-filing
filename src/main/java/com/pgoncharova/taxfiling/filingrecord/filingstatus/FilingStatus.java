package com.pgoncharova.taxfiling.filingrecord.filingstatus;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class FilingStatus implements Serializable {

    enum Status {
        SINGLE,
        MARRIED_FILING_JOINTLY,
        MARRIED_FILING_SEPARATELY,
        HEAD_OF_HOUSEHOLD,
        QUALIFYING_SURVIVING_SPOUSE
    }

    private Status status;

    // If checked... MFS, HOH, or QSS.
    private String firstName;
    private String middleInitial;
    private String lastName;
    private String ssn; // or MFJ.



}
