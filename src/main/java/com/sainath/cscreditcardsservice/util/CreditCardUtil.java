package com.sainath.cscreditcardsservice.util;

import com.sainath.cscreditcardsservice.constant.CreditCardConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CreditCardUtil {
    private static final Logger logger = LoggerFactory.getLogger(CreditCardUtil.class);

    /**
     * Finds card score based on eligibility and APR
     * @param eligibility eligibility rating
     * @param apr Annual percentage rate for the card
     * @return card score in BigDecimal
     */
    public static BigDecimal getCardScore(BigDecimal eligibility, BigDecimal apr) {
        if(eligibility != null && apr != null
                && eligibility.compareTo(BigDecimal.ZERO) != 0
                && apr.compareTo(BigDecimal.ZERO) != 0) {
            logger.info("ELIGIBILITY = {}", eligibility);
            logger.info("APR = {}", apr);

            BigDecimal cardScore = eligibility.multiply(BigDecimal.ONE.divide(apr, 4, RoundingMode.HALF_UP).pow(2))
                                                .setScale(3, RoundingMode.HALF_UP);
            logger.info("CARD SCORE = {}", cardScore);
            return cardScore;
        }
        return BigDecimal.ZERO;
    }
}
