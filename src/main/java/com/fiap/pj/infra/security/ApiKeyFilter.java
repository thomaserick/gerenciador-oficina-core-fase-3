package com.fiap.pj.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ApiKeyFilter extends OncePerRequestFilter {

    @Value("${security.internal.api-key}")
    private String apiKey;

    private static final String INTERNAL_API_PREFIX = "/interno/";
    private static final String API_KEY_HEADER = "X-API-KEY";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        if (!request.getRequestURI().startsWith(INTERNAL_API_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }
        String header = request.getHeader(API_KEY_HEADER);

        if (!apiKey.equals(header)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        filterChain.doFilter(request, response);
    }
}

