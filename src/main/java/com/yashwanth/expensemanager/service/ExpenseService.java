package com.yashwanth.expensemanager.service;

import com.yashwanth.expensemanager.dto.expense.*;
import com.yashwanth.expensemanager.entity.*;
import com.yashwanth.expensemanager.repository.*;
import com.yashwanth.expensemanager.util.MapperUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;

    public ExpenseService(ExpenseRepository expenseRepository,
                          CategoryRepository categoryRepository) {
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
    }

    // CREATE
    public ExpenseResponse create(ExpenseRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Expense expense = new Expense();
        expense.setDescription(request.getDescription());
        expense.setAmount(request.getAmount());
        expense.setDate(request.getDate());
        expense.setCategory(category);

        return MapperUtil.toResponse(expenseRepository.save(expense));
    }

    // READ (PAGINATED)
    public Page<ExpenseResponse> getAll(Pageable pageable) {
        return expenseRepository
                .findAll(pageable)
                .map(MapperUtil::toResponse);
    }
}
