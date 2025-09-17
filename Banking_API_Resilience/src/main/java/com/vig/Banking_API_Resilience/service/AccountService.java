package com.vig.Banking_API_Resilience.service;


import com.vig.Banking_API_Resilience.model.Account;
import com.vig.Banking_API_Resilience.model.User;
import com.vig.Banking_API_Resilience.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public BigDecimal getBalance(User user) {
        Account account = accountRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Account not found."));
        return account.getBalance();
    }

    public void deposit(User user, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }

        Account account = accountRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Account not found."));
        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);
    }

    public void withdraw(User user, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }

        Account account = accountRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Account not found."));

        if (account.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient balance.");
        }

        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);
    }
}
