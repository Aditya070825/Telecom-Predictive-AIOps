package com.telecom.aiops.controller;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Exposes the currently authenticated user's identity and role,
 * so the Angular frontend can determine which pages/nav links
 * to show after login, without hardcoding role logic client-side.
 */
@RestController
public class AuthController {

    @GetMapping("/api/auth/me")
    public Map<String, String> getCurrentUser(Authentication authentication) {
        String username = authentication.getName();
        String role = authentication.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("UNKNOWN");

        return Map.of("username", username, "role", role);
    }
}