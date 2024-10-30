package com.pgoncharova.taxfiling.taxpayer;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collection;

/**
 * TaxpayerPrincipal implements Spring Security's UserDetails interface allowing handling of the user's authentication
 * credentials and access roles.
 */
public class TaxpayerPrincipal implements UserDetails {

    private final Taxpayer taxpayer;

    public TaxpayerPrincipal(Taxpayer taxpayer) {
        this.taxpayer = taxpayer;
    }

    /**
     * Convert Taxpayer's roles from a space-delimited string to a list of SimpleGrantedAuthority objects.
     * Taxpayer roles are stored in a string like "admin user".
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(StringUtils.tokenizeToStringArray(this.taxpayer.getRoles(), " "))
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .toList();
    }

    @Override
    public String getPassword() {
        return this.taxpayer.getPassword();
    }

    @Override
    public String getUsername() {
        return this.taxpayer.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.taxpayer.isEnabled();
    }

    public Taxpayer getTaxpayer() {
        return taxpayer;
    }
}
