package com.yashwanth.expensemanager.controller;

import com.yashwanth.expensemanager.dto.analytics.MonthlyAnalyticsResponse;
import com.yashwanth.expensemanager.service.AnalyticsService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final AnalyticsService service;

    public AnalyticsController(AnalyticsService service) {
        this.service = service;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/month/{month}")
    public MonthlyAnalyticsResponse monthly(@PathVariable String month) {
        return service.getMonthlyAnalytics(month);
    }
}
