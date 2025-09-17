package com.vig.HDFC_Java_Assignment_Unit.Testing_final.bank;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    BankAccount account;

    @BeforeEach
    void setup() {
        account = new BankAccount();
    }

    @Test
    void testDepositValidAmount() {
        account.deposit(100);
        assertEquals(100, account.getBalance());
    }

    @Test
    void testDepositInvalidAmount() {
        assertThrows(IllegalArgumentException.class, () -> account.deposit(0));
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-50));
    }

    @Test
    void testWithdrawValid() {
        account.deposit(200);
        account.withdraw(100);
        assertEquals(100, account.getBalance());
    }

    @Test
    void testWithdrawMoreThanBalance() {
        account.deposit(100);
        assertThrows(IllegalStateException.class, () -> account.withdraw(150));
    }

    @Test
    void testGetBalanceInitial() {
        assertEquals(0, account.getBalance());
    }

    @AfterEach
    void cleanup() {
        account = null;
    }
}
