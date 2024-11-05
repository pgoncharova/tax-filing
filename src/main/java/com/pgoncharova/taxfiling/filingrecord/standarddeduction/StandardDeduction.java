package com.pgoncharova.taxfiling.filingrecord.standarddeduction;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class StandardDeduction implements Serializable {

    enum Claim {
        YOU_AS_DEPENDENT,
        YOUR_SPOUSE_AS_DEPENDENT,
        SPOUSE_ITEMIZES_ON_SEPARATE_RETURN,
        YOU_WERE_DUAL_STATUS_ALIEN
    }

    private Claim someoneCanClaim;

    private boolean youBornBefore;
    private boolean youAreBlind;
    private boolean spouseBornBefore;
    private boolean spouseIsBlind;
}
