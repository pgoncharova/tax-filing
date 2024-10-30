package com.pgoncharova.taxfiling.taxpayer;

import jakarta.transaction.Transactional;
import org.hibernate.ObjectNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TaxpayerService implements UserDetailsService {

    private final TaxpayerRepository taxpayerRepository;

    private final PasswordEncoder passwordEncoder;

    public TaxpayerService(TaxpayerRepository taxpayerRepository, PasswordEncoder passwordEncoder) {
        this.taxpayerRepository = taxpayerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Taxpayer save(Taxpayer newTaxpayer) {
        newTaxpayer.setPassword(this.passwordEncoder.encode(newTaxpayer.getPassword()));
        return this.taxpayerRepository.save(newTaxpayer);
    }

    public List<Taxpayer> findAll() {
        return this.taxpayerRepository.findAll();
    }

    public Taxpayer findById(Long taxpayerId) {
        return this.taxpayerRepository.findById(taxpayerId)
                .orElseThrow(() -> new ObjectNotFoundException("user", taxpayerId));
    }

    public Taxpayer update(Long taxpayerId, Taxpayer update) {
        Taxpayer oldTaxpayer = this.taxpayerRepository.findById(taxpayerId)
                .orElseThrow(() -> new ObjectNotFoundException("user", taxpayerId));
        oldTaxpayer.setUsername(update.getUsername());
        oldTaxpayer.setEmail(update.getEmail());
        oldTaxpayer.setRoles(update.getRoles());
        oldTaxpayer.setEnabled(update.isEnabled());
        return this.taxpayerRepository.save(oldTaxpayer);
    }

    public void delete(Long taxpayerId) {
        this.taxpayerRepository.findById(taxpayerId)
                .orElseThrow(() -> new ObjectNotFoundException("user", taxpayerId));
        this.taxpayerRepository.deleteById(taxpayerId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.taxpayerRepository.findByUsername(username)
                .map(taxpayer -> new TaxpayerPrincipal(taxpayer))
                .orElseThrow(() -> new UsernameNotFoundException("username " + username + " is not found."));
    }

    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        return this.taxpayerRepository.findByEmail(email)
                .map(taxpayer -> new TaxpayerPrincipal(taxpayer))
                .orElseThrow(() -> new UsernameNotFoundException("email " + email + " is not found."));
    }
}
