package com.yashwanth.expensemanager.dto.analytics;

import com.yashwanth.expensemanager.dto.expense.CategorySummaryDto;
import java.util.List;

public class MonthlyAnalyticsResponse {

    private Double totalSpent;
    private List<CategorySummaryDto> categoryBreakdown;

    public Double getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(Double totalSpent) {
        this.totalSpent = totalSpent;
    }

    public List<CategorySummaryDto> getCategoryBreakdown() {
        return categoryBreakdown;
    }

    public void setCategoryBreakdown(List<CategorySummaryDto> categoryBreakdown) {
        this.categoryBreakdown = categoryBreakdown;
    }
}
