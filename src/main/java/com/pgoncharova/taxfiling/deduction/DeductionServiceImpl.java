package com.pgoncharova.taxfiling.deduction;

import com.pgoncharova.taxfiling.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeductionServiceImpl implements DeductionService {

    private final DeductionRepository deductionRepository;

    @Autowired
    public DeductionServiceImpl(DeductionRepository deductionRepository) {
        this.deductionRepository = deductionRepository;
    }

    @Override
    public Deduction saveDeduction(Deduction deduction) {
        return deductionRepository.save(deduction);
    }

    @Override
    public List<Deduction> findDeductionByUser(User user) {
        return deductionRepository.findByUser(user);
    }

    @Override
    public void deleteDeduction(Long id) {
        deductionRepository.deleteById(id);
    }
}
