package com.fiap.pj.core.util.security;

import com.fiap.pj.infra.security.UserDetailsImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityContextUtils {

    private SecurityContextUtils() {
    }

    public static UsernamePasswordAuthenticationToken getAuthentication() {
        return (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
    }

    public static String getLogin() {
        UserDetails user = (UserDetailsImpl) getAuthentication().getPrincipal();
        return user.getUsername();
    }
}
