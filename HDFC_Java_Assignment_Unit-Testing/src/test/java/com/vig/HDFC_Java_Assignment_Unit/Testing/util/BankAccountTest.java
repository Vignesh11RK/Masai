package com.vig.HDFC_Java_Assignment_Unit.Testing.util;


import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    private BankAccount account;

    @BeforeEach
    void setUp() {
        account = new BankAccount();
    }

    @Test
    void testDepositPositiveAmount() {
        account.deposit(100.0);
        assertEquals(100.0, account.getBalance());
    }

    @Test
    void testDepositZeroOrNegativeThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> account.deposit(0));
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-50));
    }

    @Test
    void testWithdrawValidAmount() {
        account.deposit(200.0);
        account.withdraw(50.0);
        assertEquals(150.0, account.getBalance());
    }

    @Test
    void testWithdrawMoreThanBalanceThrowsException() {
        account.deposit(100.0);
        assertThrows(IllegalStateException.class, () -> account.withdraw(150.0));
    }

    @Test
    void testGetBalanceInitialIsZero() {
        assertEquals(0.0, account.getBalance());
    }

    @AfterEach
    void tearDown() {
        account = null;
    }
}

