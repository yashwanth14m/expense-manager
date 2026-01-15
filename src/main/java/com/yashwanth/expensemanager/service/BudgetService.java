package com.yashwanth.expensemanager.service;

import com.yashwanth.expensemanager.dto.budget.BudgetRequest;
import com.yashwanth.expensemanager.dto.budget.BudgetResponse;
import com.yashwanth.expensemanager.entity.Budget;
import com.yashwanth.expensemanager.entity.User;
import com.yashwanth.expensemanager.repository.BudgetRepository;
import com.yashwanth.expensemanager.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.YearMonth;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final UserRepository userRepository;

    public BudgetService(BudgetRepository budgetRepository,
                         UserRepository userRepository) {
        this.budgetRepository = budgetRepository;
        this.userRepository = userRepository;
    }

    public BudgetResponse create(BudgetRequest request) {

        // 1️⃣ Identify logged-in user
        String email = (String) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2️⃣ Parse month
        YearMonth month = YearMonth.parse(request.getMonth());

        // 3️⃣ Prevent duplicate budget per month
        budgetRepository.findByUserAndMonth(user, month)
                .ifPresent(b -> {
                    throw new RuntimeException("Budget already exists for this month");
                });

        // 4️⃣ Create budget
        Budget budget = new Budget();
        budget.setUser(user);
        budget.setMonth(month);
        budget.setAmount(request.getAmount());

        Budget saved = budgetRepository.save(budget);

        // 5️⃣ Map to response
        BudgetResponse response = new BudgetResponse();
        response.setId(saved.getId());
        response.setMonth(saved.getMonth().toString());
        response.setAmount(saved.getAmount());

        return response;
    }

    public BudgetResponse getByMonth(String monthStr) {

        String email = (String) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        YearMonth month = YearMonth.parse(monthStr);

        Budget budget = budgetRepository.findByUserAndMonth(user, month)
                .orElseThrow(() -> new RuntimeException("Budget not set for this month"));

        BudgetResponse response = new BudgetResponse();
        response.setId(budget.getId());
        response.setMonth(budget.getMonth().toString());
        response.setAmount(budget.getAmount());

        return response;
    }
}
