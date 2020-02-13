package com.sainath.cscreditcardsservice.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class CreditCardUtilTest {

    // Null check
    @Test
    public void testGetCardScoreForNull() {
        assertEquals(CreditCardUtil.getCardScore(null, new BigDecimal("21.4")), BigDecimal.ZERO);
    }

    // Zero check
    @Test
    public void testGetCardScoreForZero() {
        assertEquals(CreditCardUtil.getCardScore(BigDecimal.ZERO, new BigDecimal("21.4")), BigDecimal.ZERO);
    }

    // Value check hardcoded calculated with round up 3rd decimal point
    @Test
    public void testGetCardScoreForValue() {
        assertEquals(CreditCardUtil.getCardScore(new BigDecimal("63.0"), new BigDecimal("21.4")), new BigDecimal("0.137"));
    }
}