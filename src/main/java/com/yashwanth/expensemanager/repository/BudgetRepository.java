package com.yashwanth.expensemanager.repository;

import com.yashwanth.expensemanager.entity.Budget;
import com.yashwanth.expensemanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.YearMonth;
import java.util.Optional;

public interface BudgetRepository extends JpaRepository<Budget, Long> {

    Optional<Budget> findByUserAndMonth(User user, YearMonth month);
}

