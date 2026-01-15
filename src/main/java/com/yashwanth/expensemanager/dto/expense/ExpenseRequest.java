package com.yashwanth.expensemanager.dto.expense;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class ExpenseRequest {

    @NotBlank
    private String description;

    @Positive
    private Double amount;

    @NotNull
    private LocalDate date;

    @NotNull
    private Long categoryId;

    public String getDescription() { return description; }
    public Double getAmount() { return amount; }
    public LocalDate getDate() { return date; }
    public Long getCategoryId() { return categoryId; }

    public void setDescription(String description) { this.description = description; }
    public void setAmount(Double amount) { this.amount = amount; }
    public void setDate(LocalDate date) { this.date = date; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
}
