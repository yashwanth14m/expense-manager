package com.yashwanth.expensemanager.util;

import com.yashwanth.expensemanager.dto.expense.ExpenseResponse;
import com.yashwanth.expensemanager.entity.Expense;

public class MapperUtil {

    public static ExpenseResponse toResponse(Expense expense) {
        ExpenseResponse response = new ExpenseResponse();
        response.setId(expense.getId());
        response.setDescription(expense.getDescription());
        response.setAmount(expense.getAmount());
        response.setDate(expense.getDate());
        response.setCategory(expense.getCategory().getName());
        return response;
    }
}

