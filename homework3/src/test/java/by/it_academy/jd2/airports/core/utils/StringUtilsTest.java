package by.it_academy.jd2.airports.core.utils;

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


    @Test
    void isAllNotNullOrEmpty() {
        assertFalse(StringUtils.isAllNotNullOrEmpty(null, null));
        assertFalse(StringUtils.isAllNotNullOrEmpty("", ""));
        assertFalse(StringUtils.isAllNotNullOrEmpty("", null));
        assertFalse(StringUtils.isAllNotNullOrEmpty(" ", null));
        assertFalse(StringUtils.isAllNotNullOrEmpty("value", null));
        assertFalse(StringUtils.isAllNotNullOrEmpty(null, "value"));
        assertTrue(StringUtils.isAllNotNullOrEmpty(" ", " "));
        assertTrue(StringUtils.isAllNotNullOrEmpty("value", "value"));
    }

    @Test
    void isAllNullOrEmpty() {
        assertTrue(StringUtils.isAllNullOrEmpty(null, null));
        assertTrue(StringUtils.isAllNullOrEmpty("", ""));
        assertTrue(StringUtils.isAllNullOrEmpty("", null));
        assertFalse(StringUtils.isAllNullOrEmpty(" ", null));
        assertFalse(StringUtils.isAllNullOrEmpty("value", null));
        assertFalse(StringUtils.isAllNullOrEmpty(null, "value"));
        assertFalse(StringUtils.isAllNullOrEmpty(" ", " "));
        assertFalse(StringUtils.isAllNullOrEmpty("value", "value"));
    }
}