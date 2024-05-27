package com.rookie.rookiee.config;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    // @Override
    // protected boolean shouldNotFilter(HttpServletRequest request) throws
    // ServletException {
    // // TODO Auto-generated method stub
    // return super.shouldNotFilter(request);
    // }

    private final UserAuthProvider userAuthProvider;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println(request.getRequestURI());
        if (header != null) {
            String[] elements = header.split(" ");
            if (elements.length == 2 && "Bearer".equals(elements[0])) {
                try {

                    SecurityContextHolder.getContext().setAuthentication(
                            userAuthProvider.validateToken(elements[1]));

                } catch (RuntimeException e) {
                    SecurityContextHolder.clearContext();

                    throw e;
                }
            }
        }
        filterChain.doFilter(request, response);
    }

}
