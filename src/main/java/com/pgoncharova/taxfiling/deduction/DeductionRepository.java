package com.pgoncharova.taxfiling.deduction;

import com.pgoncharova.taxfiling.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DeductionRepository extends JpaRepository<Deduction, Long> {

    List<Deduction> findByUser(User user);
}
