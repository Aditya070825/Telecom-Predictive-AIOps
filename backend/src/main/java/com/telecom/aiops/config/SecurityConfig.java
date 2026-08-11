package com.telecom.aiops.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configures role-based access control (Phase 16 connector) using
 * in-memory test users, one per role. Each role is restricted to the
 * REST endpoints backing the pages it should be able to see:
 *
 * ROLE_A -> /api/dashboard, /api/incidents
 * ROLE_B -> everything except /api/executive
 * ROLE_C -> /api/executive only
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder encoder) {
        UserDetails userA = User.builder()
                .username("usera")
                .password(encoder.encode("passA123"))
                .roles("A")
                .build();

        UserDetails userB = User.builder()
                .username("userb")
                .password(encoder.encode("passB123"))
                .roles("B")
                .build();

        UserDetails userC = User.builder()
                .username("userc")
                .password(encoder.encode("passC123"))
                .roles("C")
                .build();

        return new InMemoryUserDetailsManager(userA, userB, userC);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/executive/**").hasRole("C")
                .requestMatchers("/api/dashboard/**", "/api/incidents/**").hasAnyRole("A", "B")
                .requestMatchers("/api/kpi/**", "/api/risk/**", "/api/forecast/**", "/api/automation/**").hasRole("B")
                .anyRequest().authenticated()
            )
        .formLogin(form -> form.permitAll())
        .httpBasic(basic -> {})
        .csrf(csrf -> csrf.disable());

        return http.build();
    }
}