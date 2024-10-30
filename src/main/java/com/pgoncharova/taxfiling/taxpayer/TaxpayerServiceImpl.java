package com.pgoncharova.taxfiling.taxpayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaxpayerServiceImpl implements TaxpayerService {

    private final TaxpayerRepository taxpayerRepository;

    @Autowired
    public TaxpayerServiceImpl(TaxpayerRepository taxpayerRepository) {
        this.taxpayerRepository = taxpayerRepository;
    }

    @Override
    public Taxpayer saveTaxpayer(Taxpayer taxpayer) {
        return taxpayerRepository.save(taxpayer);
    }

    @Override
    public Optional<Taxpayer> findById(Long taxpayerId) {
        return taxpayerRepository.findById(taxpayerId);
    }

    @Override
    public Optional<Taxpayer> findByUsername(String username) {
        return taxpayerRepository.findByUsername(username);
    }

    @Override
    public Optional<Taxpayer> findByEmail(String email) {
        return taxpayerRepository.findByEmail(email);
    }

    @Override
    public void deleteTaxpayer(Long id) {
        taxpayerRepository.deleteById(id);
    }
}
