package com.pgoncharova.taxfiling.filingrecord.address;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class Address implements Serializable {

    private boolean isPOBox;
    private boolean isForeign;

    private String homeAddress;
    private String apartmentNumber;
    private String city;
    private String state;
    private String zipCode;

    private String foreignCountryName;
    private String foreignProvinceStateCounty;
    private String foreignPostalCode;
}
