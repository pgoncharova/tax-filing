package com.pgoncharova.taxfiling.taxpayer;

import jakarta.validation.constraints.NotEmpty;

public record TaxpayerDto(Long id,
                          String username,
                          String password,
                          @NotEmpty(message = "email is required.")
                          String email,
                          String role,
                          Integer numberOfFilingRecords){
}
