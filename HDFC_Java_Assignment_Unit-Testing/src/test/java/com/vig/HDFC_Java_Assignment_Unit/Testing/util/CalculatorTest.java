package com.vig.HDFC_Java_Assignment_Unit.Testing.util;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    void testAdd() {
        assertEquals(5, calculator.add(2, 3));
        assertEquals(-1, calculator.add(2, -3));
    }

    @ParameterizedTest
    @CsvSource({
            "2, 3, 5",
            "10, 20, 30",
            "7, 8, 15"
    })
    void testAddParameterized(int a, int b, int expected) {
        assertEquals(expected, calculator.add(a, b));
    }

    @Test
    void testSubtract() {
        assertEquals(1, calculator.subtract(4, 3));
    }

    @Test
    void testMultiply() {
        assertEquals(6, calculator.multiply(2, 3));
    }

    @Test
    void testDivideValid() {
        assertEquals(2, calculator.divide(6, 3));
    }

    @Test
    void testDivideByZeroThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> calculator.divide(4, 0));
    }

    @AfterEach
    void tearDown() {
        calculator = null;
    }
}
