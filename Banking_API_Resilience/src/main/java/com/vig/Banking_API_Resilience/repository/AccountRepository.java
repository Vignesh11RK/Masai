package com.vig.Banking_API_Resilience.repository;

import com.vig.Banking_API_Resilience.model.Account;
import com.vig.Banking_API_Resilience.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUser(User user);
}
