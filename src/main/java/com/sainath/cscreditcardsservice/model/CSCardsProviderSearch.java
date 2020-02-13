package com.sainath.cscreditcardsservice.model;

public class CSCardsProviderSearch {
    private String name;
    private Integer creditScore;

    public CSCardsProviderSearch() {
    }

    public CSCardsProviderSearch(String name, Integer creditScore) {
        this.name = name;
        this.creditScore = creditScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Integer creditScore) {
        this.creditScore = creditScore;
    }

    @Override
    public String toString() {
        return "CSCardsProviderSearch{" +
                "name='" + name + '\'' +
                ", creditScore=" + creditScore +
                '}';
    }
}
