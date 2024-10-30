package com.pgoncharova.taxfiling.taxpayer;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TaxpayerToTaxpayerDtoConverter implements Converter<Taxpayer, TaxpayerDto> {

    @Override
    public TaxpayerDto convert(Taxpayer source) {
        TaxpayerDto taxpayerDto = new TaxpayerDto(source.getId(),
                source.getUsername(), source.getPassword(),
                source.getEmail(), source.getRole(),
                source.getNumberOfFilingRecords());
        return taxpayerDto;
    }
}
