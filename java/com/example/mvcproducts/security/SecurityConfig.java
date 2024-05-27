package com.example.mvcproducts.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/favicon.ico", "/css/**", "/images/**").permitAll() // Static resources
                        .requestMatchers(HttpMethod.GET, "/").permitAll() // Allow guest access to home page
                        .requestMatchers(HttpMethod.GET, "/products/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/cart/**", "/checkout/success").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/products/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/cart/**", "/checkout/success", "/cart/update").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/login").permitAll() // Permit access to login page for every user
                        .requestMatchers("/register").permitAll() // Permit access to register page for every user
                        .requestMatchers("/forgot").permitAll() // Permit access to forgot page without authentication
                        .requestMatchers("/admin").hasRole("ADMIN") // Only admin can access admin page
                        .anyRequest().authenticated() // All other requests need to be authenticated
                )
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true) // Redirect to home page after successful login
                        .failureUrl("/login?error=true"))
                .logout(logout -> logout
                        .logoutSuccessUrl("/"))
                .csrf(csrf -> csrf.disable()); // Disable CSRF protection for simplicity (not recommended for production)

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ProviderManager authManagerBean(HttpSecurity security) throws Exception {
        return (ProviderManager) security.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(authProvider())
                .build();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}
