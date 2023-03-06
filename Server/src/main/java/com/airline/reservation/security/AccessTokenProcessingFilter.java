package com.airline.reservation.security;

import static com.airline.reservation.security.AccessTokenUserDetailsService.PURPOSE_ACCESS_TOKEN;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import javax.servlet.http.HttpServletRequest;

public class AccessTokenProcessingFilter extends AbstractPreAuthenticatedProcessingFilter {

    private static final Pattern AUTH_PATTERN = Pattern.compile("Airline ([0-9a-f]+)");

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        String authHeader = request.getHeader("authorization");
        if (authHeader == null) {
            return null;
        }

        Matcher matcher = AUTH_PATTERN.matcher(authHeader);
        if (!matcher.matches()) {
            return null;
        }

        return matcher.group(1);
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return PURPOSE_ACCESS_TOKEN;
    }
}
