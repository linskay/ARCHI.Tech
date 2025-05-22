package com.coworking.service.impl;

import com.coworking.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private static final List<String> EXCLUDED_PATHS = List.of(
            "/swagger-ui/",
            "/v3/api-docs",
            "/api-docs",
            "/swagger-resources",
            "/webjars/",
            "/auth/"
    );

    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final String SECRET_KEY = "your_strong_secret_key_here_123!";

    public JwtRequestFilter(UserDetailsService userDetailsService,
                            UserRepository userRepository) {
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        final String requestURI = request.getRequestURI();

        // Пропускаем Swagger и публичные эндпоинты
        if (isExcludedPath(requestURI)) {
            chain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(SECRET_KEY.getBytes())
                        .parseClaimsJws(jwt)
                        .getBody();

                username = claims.getSubject();
                log.debug("JWT token validated for user: {}", username);

            } catch (ExpiredJwtException ex) {
                log.warn("JWT token expired: {}", ex.getMessage());
            } catch (SignatureException ex) {
                log.error("Invalid JWT signature: {}", ex.getMessage());
            } catch (Exception ex) {
                log.error("JWT processing error: {}", ex.getMessage());
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (userDetails != null && userRepository.existsByEmail(username)) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
                log.debug("Authenticated user: {}", username);
            }
        }

        chain.doFilter(request, response);
    }

    private boolean isExcludedPath(String requestURI) {
        return EXCLUDED_PATHS.stream().anyMatch(requestURI::contains);
    }
}