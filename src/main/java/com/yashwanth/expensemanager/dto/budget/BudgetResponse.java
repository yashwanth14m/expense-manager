package com.yashwanth.expensemanager.dto.budget;

public class BudgetResponse {

    private Long id;
    private String month;
    private Double amount;

    public Long getId() { return id; }
    public String getMonth() { return month; }
    public Double getAmount() { return amount; }

    public void setId(Long id) { this.id = id; }
    public void setMonth(String month) { this.month = month; }
    public void setAmount(Double amount) { this.amount = amount; }
}

