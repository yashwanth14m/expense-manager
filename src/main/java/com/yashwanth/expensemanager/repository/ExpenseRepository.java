package com.yashwanth.expensemanager.repository;

import com.yashwanth.expensemanager.dto.expense.CategorySummaryDto;
import com.yashwanth.expensemanager.entity.Expense;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query("""
        SELECT new com.yashwanth.expensemanager.dto.expense.CategorySummaryDto(
            e.category.name,
            SUM(e.amount)
        )
        FROM Expense e
        WHERE e.user.email = :email
          AND MONTH(e.date) = :month
          AND YEAR(e.date) = :year
        GROUP BY e.category.name
    """)
    List<CategorySummaryDto> monthlyCategorySummary(
            @Param("email") String email,
            @Param("month") int month,
            @Param("year") int year
    );

    @Query("""
    SELECT COALESCE(SUM(e.amount), 0)
    FROM Expense e
    WHERE e.user.email = :email
      AND MONTH(e.date) = :month
      AND YEAR(e.date) = :year
""")
    Double monthlyTotal(
            @Param("email") String email,
            @Param("month") int month,
            @Param("year") int year
    );

    Page<Expense> findAll(Pageable pageable);
}
