package com.github.biancacristina.Forum.config;

import com.github.biancacristina.Forum.security.JWTAuthenticationFilter;
import com.github.biancacristina.Forum.security.JWTAuthorizationFilter;
import com.github.biancacristina.Forum.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment env;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    // Define which paths aren't block for public
    private static final String[] PUBLIC_MATCHERS = {
            "/h2-console/**",
    };

    // Define which paths aren't block for the public as read-only(GET)
    private static final String[] PUBLIC_MATCHERS_GET = {
            "/categories/**",
            "/topics/**",
            "/messages/**"
    };

    // Define which paths aren't block for the public to insert (POST)
    private static final String[] PUBLIC_MATCHERS_POST = {
            "/users"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Verify if profile test is actived, if so acess H2
        if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
            http.headers().frameOptions().disable(); // Libera o acesso ao H2
        }

        // Disable CSRF protection
        http.cors().and().csrf().disable();

        /*
            Allows all paths from vector "PUBLIC_MATCHERS" and
            request permission for all others (/users for instance)
        */
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
                .antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
                .antMatchers(PUBLIC_MATCHERS).permitAll()
                .anyRequest()
                .authenticated();

        // Register JWTAuthenticationFilter and JWTAuthorizationFilter
        http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
        http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));

        // Don't create session for user (work as a kind of "stateless proxy")
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Define who is the user and how password will be encrypted
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        // Allows acess to endpoints from multiples sources using basic configuration
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

