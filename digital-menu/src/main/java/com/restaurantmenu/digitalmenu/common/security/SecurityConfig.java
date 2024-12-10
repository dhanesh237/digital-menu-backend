package com.restaurantmenu.digitalmenu.common.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/api/public/**", "/api/v1/**")  // Example: Ignore CSRF protection for public endpoints
                )
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/api/public/**").permitAll()  // Public endpoints accessible without authentication
                                .anyRequest().authenticated()  // All other requests require authentication
                )
                .httpBasic(customizer -> {});  // Basic Authentication enabled with no custom settings

        return http.build();
    }
}
