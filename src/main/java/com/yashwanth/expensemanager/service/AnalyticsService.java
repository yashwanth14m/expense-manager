package com.yashwanth.expensemanager.service;

import com.yashwanth.expensemanager.dto.analytics.MonthlyAnalyticsResponse;
import com.yashwanth.expensemanager.repository.ExpenseRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.YearMonth;

@Service
public class AnalyticsService {

    private final ExpenseRepository expenseRepository;

    public AnalyticsService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public MonthlyAnalyticsResponse getMonthlyAnalytics(String monthStr) {

        String email = (String) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        YearMonth month = YearMonth.parse(monthStr);

        Double totalSpent = expenseRepository.monthlyTotal(
                email,
                month.getMonthValue(),
                month.getYear()
        );

        var categorySummary = expenseRepository.monthlyCategorySummary(
                email,
                month.getMonthValue(),
                month.getYear()
        );

        MonthlyAnalyticsResponse response = new MonthlyAnalyticsResponse();
        response.setTotalSpent(totalSpent);
        response.setCategoryBreakdown(categorySummary);

        return response;
    }
}
