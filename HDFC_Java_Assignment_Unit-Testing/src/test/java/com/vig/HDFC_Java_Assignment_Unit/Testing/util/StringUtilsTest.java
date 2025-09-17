package com.vig.HDFC_Java_Assignment_Unit.Testing.util;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    private StringUtils utils;

    @BeforeEach
    void init() {
        utils = new StringUtils();
    }

    @ParameterizedTest
    @CsvSource({
            "madam, true",
            "racecar, true",
            "hello, false",
            "noon, true",
            "'', true",
            "abc, false"
    })
    void testIsPalindrome(String input, boolean expected) {
        assertEquals(expected, utils.isPalindrome(input));
    }

    @Test
    void testReverse() {
        assertEquals("cba", utils.reverse("abc"));
        assertEquals("", utils.reverse(""));
        assertNull(utils.reverse(null));
    }

    @Test
    void testIsBlank() {
        assertTrue(utils.isBlank(null));
        assertTrue(utils.isBlank(" "));
        assertTrue(utils.isBlank("   "));
        assertFalse(utils.isBlank("hello"));
    }

    @AfterEach
    void destroy() {
        utils = null;
    }
}
