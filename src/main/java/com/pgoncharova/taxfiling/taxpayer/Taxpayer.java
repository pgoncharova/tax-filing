package com.pgoncharova.taxfiling.taxpayer;

import com.pgoncharova.taxfiling.filingrecord.FilingRecord;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Taxpayer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String roles; // Could be "user" or "admin"

    @Column(nullable = false)
    private boolean enabled;

    @Column(unique = true, nullable = false, length = 11)
    private String ssn; // eg. 123-45-6789

    private String firstName;
    private String middleName;
    private String lastName;

    private Integer streetNumberAddress;
    private String streetNameAddress;
    private String unitAddress; // Apartment, suite, etc.
    private String cityAddress;
    private String stateAddress;
    private String zipAddress;

    @Column(length = 12)
    private String phoneNumber; // eg. 123-456-7899

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @OneToMany(mappedBy = "taxpayer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FilingRecord> filingRecords = new ArrayList<>();

    public Integer getNumberOfFilingRecords() {
        return this.filingRecords.size();
    }
}
