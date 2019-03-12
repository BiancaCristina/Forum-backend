package com.github.biancacristina.Forum.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter{

    private JWTUtil jwtUtil;
    private UserDetailsService userDetailsService; // Usado para buscar o usuario quando quero checar o token

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization"); // Get the token

        if (header != null && header.startsWith("Bearer ")) {
            // Get the token without "Bearer "
            UsernamePasswordAuthenticationToken auth = getAuthentication(header.substring(7));

            if (auth != null) {
                // If auth is null means that the token is invalid
                // When the token isn't null, the acess is allowed
                SecurityContextHolder.getContext().setAuthentication(auth);
                // Fim do comando
            }
        }

        // Continues the request after the tests
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        if(jwtUtil.validToken(token)) {
            // If the token is valid, return an authenticated user

            String username  = jwtUtil.getUsername(token);
            UserDetails user = userDetailsService.loadUserByUsername(username);
            return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        }
        return null;
    }

}
