package com.yashwanth.expensemanager.dto.expense;

import java.time.LocalDate;

public class ExpenseResponse {

    private Long id;
    private String description;
    private Double amount;
    private LocalDate date;
    private String category;

    public Long getId() { return id; }
    public String getDescription() { return description; }
    public Double getAmount() { return amount; }
    public LocalDate getDate() { return date; }
    public String getCategory() { return category; }

    public void setId(Long id) { this.id = id; }
    public void setDescription(String description) { this.description = description; }
    public void setAmount(Double amount) { this.amount = amount; }
    public void setDate(LocalDate date) { this.date = date; }
    public void setCategory(String category) { this.category = category; }
}

