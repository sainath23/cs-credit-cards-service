package com.sainath.cscreditcardsservice.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreditCardSearch {

    @NotBlank(message = "Full name is required")
    private String name;

    @NotNull
    @Min(value = 0, message = "Credit score must be between 0 to 700")
    @Max(value = 700, message = "Credit score must be between 0 to 700")
    private Integer creditScore;

    @NotNull
    @Min(value = 0, message = "Salary should not be less than zero")
    @Max(value = Integer.MAX_VALUE, message = "Max integer limit exceeded")
    private Integer salary;

    public CreditCardSearch() {
    }

    public CreditCardSearch(String name, Integer creditScore, Integer salary) {
        this.name = name;
        this.creditScore = creditScore;
        this.salary = salary;
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

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "CreditCardSearch{" +
                "name='" + name + '\'' +
                ", creditScore=" + creditScore +
                ", salary=" + salary +
                '}';
    }
}
