package com.vig.Banking_API_Resilience.controller;


import com.vig.Banking_API_Resilience.service.AccountService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @GetMapping("/balance")
    public BigDecimal getBalance(@AuthenticationPrincipal User user) {
        return accountService.getBalance(user);
    }

    @PostMapping("/deposit")
    public String deposit(@AuthenticationPrincipal User user, @RequestBody TransactionRequest request) {
        accountService.deposit(user, request.getAmount());
        logger.info("User {} deposited amount: {}", user.getUsername(), request.getAmount());
        return "Deposit successful!";
    }

    @PostMapping("/withdraw")
    @RateLimiter(name = "withdraw", fallbackMethod = "withdrawFallback")
    public String withdraw(@AuthenticationPrincipal User user, @RequestBody TransactionRequest request) {
        accountService.withdraw(user, request.getAmount());
        logger.info("User {} withdrew amount: {}", user.getUsername(), request.getAmount());
        return "Withdrawal successful!";
    }

    public String withdrawFallback(User user, TransactionRequest request, Throwable t) {
        logger.warn("Rate limit exceeded for user {}", user.getUsername());
        return "Too many withdrawal requests. Please try again later.";
    }

    @Data
    static class TransactionRequest {
        private BigDecimal amount;
    }
}
