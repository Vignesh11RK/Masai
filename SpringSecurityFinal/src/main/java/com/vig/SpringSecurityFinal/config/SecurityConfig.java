package com.vig.SpringSecurityFinal.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        manager.createUser(User.withUsername("admin")
                .password(encoder.encode("admin123"))
                .roles("ADMIN")
                .build());

        manager.createUser(User.withUsername("librarian")
                .password(encoder.encode("lib123"))
                .roles("LIBRARIAN")
                .build());

        manager.createUser(User.withUsername("student")
                .password(encoder.encode("student123"))
                .roles("STUDENT")
                .build());

        manager.createUser(User.withUsername("guest")
                .password(encoder.encode("guest123"))
                .roles("GUEST")
                .build());

        return manager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/about", "/login", "/books/public", "/css/**").permitAll()
                        .requestMatchers("/books/**").hasAnyRole("STUDENT", "LIBRARIAN", "ADMIN")
                        .requestMatchers("/reservations/**").hasAnyRole("LIBRARIAN", "ADMIN")
                        .requestMatchers("/admin/**", "/users", "/books/**/delete").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                .exceptionHandling(ex -> ex
                        .accessDeniedPage("/access-denied")
                );

        return http.build();
    }

}

