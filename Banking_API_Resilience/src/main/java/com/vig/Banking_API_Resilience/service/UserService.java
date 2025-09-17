package com.vig.Banking_API_Resilience.service;



import com.vig.Banking_API_Resilience.model.Account;
import com.vig.Banking_API_Resilience.model.User;
import com.vig.Banking_API_Resilience.repository.AccountRepository;
import com.vig.Banking_API_Resilience.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public User registerUser(String username, String password) {
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username already exists.");
        }

        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .build();

        // Save user first to get ID
        User savedUser = userRepository.save(user);

        // Create account with default balance 0.0
        Account account = Account.builder()
                .user(savedUser)
                .balance(BigDecimal.ZERO)
                .build();

        accountRepository.save(account);

        return savedUser;
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
