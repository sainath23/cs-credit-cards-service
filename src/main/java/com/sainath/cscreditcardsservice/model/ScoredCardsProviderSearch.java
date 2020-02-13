package com.sainath.cscreditcardsservice.model;

public class ScoredCardsProviderSearch {
    private String name;
    private Integer score;
    private Integer salary;

    public ScoredCardsProviderSearch() {
    }

    public ScoredCardsProviderSearch(String name, Integer score, Integer salary) {
        this.name = name;
        this.score = score;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "ScoredCardsProviderSearch{" +
                "name='" + name + '\'' +
                ", score=" + score +
                ", salary=" + salary +
                '}';
    }
}
