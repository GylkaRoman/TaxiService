package org.example.taxiservice.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.taxiservice.model.User;
import org.example.taxiservice.repository.DriverRepository;
import org.example.taxiservice.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final DriverRepository driverRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getServletPath();

        // Пропускаем /auth/**
        if (path.startsWith("/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);

        try {
            Claims claims = jwtUtil.extractClaims(token);
            String username = claims.getSubject();

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                User foundUser = userRepository.findByUsername(username).orElse(null);
                if (foundUser == null) {
                    foundUser = driverRepository.findByUsername(username).orElse(null);
                }

                if (foundUser != null && !jwtUtil.isTokenExpired(token)) {

                    SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + foundUser.getRole().name());

                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(
                                    foundUser.getUsername(),
                                    null,
                                    List.of(authority)
                            );
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        } catch (Exception e) {
            System.out.println("JWT verification failed: " + e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}
