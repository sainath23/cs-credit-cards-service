package com.sainath.cscreditcardsservice.model;

import java.math.BigDecimal;

public class CSCardsProvider {
    private String cardName;
    private BigDecimal apr;
    private BigDecimal eligibility;

    public CSCardsProvider() {
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public BigDecimal getApr() {
        return apr;
    }

    public void setApr(BigDecimal apr) {
        this.apr = apr;
    }

    public BigDecimal getEligibility() {
        return eligibility;
    }

    public void setEligibility(BigDecimal eligibility) {
        this.eligibility = eligibility;
    }
}
