package com.yashwanth.expensemanager.controller;

import com.yashwanth.expensemanager.dto.expense.ExpenseRequest;
import com.yashwanth.expensemanager.dto.expense.ExpenseResponse;
import com.yashwanth.expensemanager.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService service;

    public ExpenseController(ExpenseService service) {
        this.service = service;
    }

    // CREATE
    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ExpenseResponse create(@Valid @RequestBody ExpenseRequest request) {
        return service.create(request);
    }

    // READ (PAGINATED)
    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public Page<ExpenseResponse> getAll(Pageable pageable) {
        return service.getAll(pageable);
    }
}


