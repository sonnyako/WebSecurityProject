package com.example.websecurity.config;

import com.example.websecurity.security.JwtUserDetails;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("default-auditor")
public class DefaultAuditor implements AuditorAware<String> {

    public static final String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";
    public static final String SYSTEM_AUDITOR = "1L";

    @Override
    public Optional<String> getCurrentAuditor() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null
                || !authentication.isAuthenticated()
                || authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals(ROLE_ANONYMOUS))
        ) return Optional.of(SYSTEM_AUDITOR);

        return Optional.of(((JwtUserDetails) authentication.getPrincipal()).getUser().getUsername());

    }
}
