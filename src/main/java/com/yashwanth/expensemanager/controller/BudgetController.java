package com.yashwanth.expensemanager.controller;

import com.yashwanth.expensemanager.dto.budget.BudgetRequest;
import com.yashwanth.expensemanager.dto.budget.BudgetResponse;
import com.yashwanth.expensemanager.service.BudgetService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    private final BudgetService service;

    public BudgetController(BudgetService service) {
        this.service = service;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public BudgetResponse create(@Valid @RequestBody BudgetRequest request) {
        return service.create(request);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/month/{month}")
    public BudgetResponse getByMonth(@PathVariable String month) {
        return service.getByMonth(month);
    }
}
