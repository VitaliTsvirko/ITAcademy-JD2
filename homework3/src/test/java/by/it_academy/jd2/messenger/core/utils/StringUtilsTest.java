package by.it_academy.jd2.messenger.core.utils;

import by.it_academy.jd2.airports.core.utils.StringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void isNullOrEmpty() {
        assertTrue(StringUtils.isNullOrEmpty(null));
        assertTrue(StringUtils.isNullOrEmpty(""));
        assertFalse(StringUtils.isNullOrEmpty(" "));
    }

    @Test
    void isAnyNullOrEmpty() {
        assertTrue(StringUtils.isAnyNullOrEmpty(null, null));
        assertTrue(StringUtils.isAnyNullOrEmpty("", ""));
        assertTrue(StringUtils.isAnyNullOrEmpty("", null));
        assertTrue(StringUtils.isAnyNullOrEmpty(" ", null));
        assertTrue(StringUtils.isAnyNullOrEmpty("value", null));
        assertFalse(StringUtils.isAnyNullOrEmpty(" ", " "));
        assertFalse(StringUtils.isAnyNullOrEmpty("value", "value"));
    }
}