package com.yashwanth.expensemanager.dto.budget;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class BudgetRequest {

    @NotNull
    private String month; // yyyy-MM

    @Positive
    private Double amount;

    public String getMonth() { return month; }
    public Double getAmount() { return amount; }

    public void setMonth(String month) { this.month = month; }
    public void setAmount(Double amount) { this.amount = amount; }
}

