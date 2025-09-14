package com.library.Spring_sec.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
public class SecurityConfig {

    // Define the in-memory user details service
    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.withUsername("admin").password(passwordEncoder().encode("admin123")).roles("ADMIN").build(),
                User.withUsername("librarian").password(passwordEncoder().encode("lib123")).roles("LIBRARIAN").build(),
                User.withUsername("student").password(passwordEncoder().encode("student123")).roles("STUDENT").build(),
                User.withUsername("guest").password(passwordEncoder().encode("guest123")).roles("GUEST").build()
        );
    }

    // Define the password encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}