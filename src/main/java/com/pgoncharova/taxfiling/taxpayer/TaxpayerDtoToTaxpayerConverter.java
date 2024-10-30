package com.pgoncharova.taxfiling.taxpayer;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TaxpayerDtoToTaxpayerConverter implements Converter<TaxpayerDto, Taxpayer> {

    @Override
    public Taxpayer convert(TaxpayerDto source) {
        Taxpayer taxpayer = new Taxpayer();
        taxpayer.setId(source.id());
        taxpayer.setUsername(source.username());
        taxpayer.setPassword(source.password());
        taxpayer.setEmail(source.email());
        taxpayer.setRole(source.role());
        return taxpayer;
    }
}
