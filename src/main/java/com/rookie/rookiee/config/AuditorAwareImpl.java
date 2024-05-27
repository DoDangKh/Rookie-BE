package com.rookie.rookiee.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.nimbusds.jose.proc.SecurityContext;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || auth.isAuthenticated())
            return Optional.ofNullable("");

        return Optional.ofNullable(auth.getName());

    }

}
