package com.sainath.cscreditcardsservice.model;

import java.math.BigDecimal;

public class ScoredCardsProvider {
    private String card;
    private BigDecimal apr;
    private BigDecimal approvalRating;

    public ScoredCardsProvider() {
    }

    public ScoredCardsProvider(String card, BigDecimal apr, BigDecimal approvalRating) {
        this.card = card;
        this.apr = apr;
        this.approvalRating = approvalRating;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public BigDecimal getApr() {
        return apr;
    }

    public void setApr(BigDecimal apr) {
        this.apr = apr;
    }

    public BigDecimal getApprovalRating() {
        return approvalRating;
    }

    public void setApprovalRating(BigDecimal approvalRating) {
        this.approvalRating = approvalRating;
    }
}
