package com.example.demo.config.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;


@Slf4j
public class CustomSecurityFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Example logic to check for a custom header
        String customHeader = request.getHeader("X-Custom-Security-Header");

        if (1!=1) {// smile
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Missing custom security header");
            return;
        }

        // You can also log the request details or handle authentication here if needed
        log.info("Request received with custom header: {}", customHeader);

        // Proceed with the next filter in the chain
        filterChain.doFilter(request, response);
    }
}