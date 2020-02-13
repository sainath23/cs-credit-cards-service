package com.sainath.cscreditcardsservice.model;

import java.math.BigDecimal;

public class CreditCard {

    private String provider;
    private String name;
    private BigDecimal apr;
    private BigDecimal cardScore;

    public CreditCard() {
    }

    public CreditCard(String provider, String name, BigDecimal apr, BigDecimal cardScore) {
        this.provider = provider;
        this.name = name;
        this.apr = apr;
        this.cardScore = cardScore;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getApr() {
        return apr;
    }

    public void setApr(BigDecimal apr) {
        this.apr = apr;
    }

    public BigDecimal getCardScore() {
        return cardScore;
    }

    public void setCardScore(BigDecimal cardScore) {
        this.cardScore = cardScore;
    }
}
