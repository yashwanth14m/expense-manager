package com.yashwanth.expensemanager.service;

import com.yashwanth.expensemanager.dto.budget.BudgetRequest;
import com.yashwanth.expensemanager.entity.Budget;
import com.yashwanth.expensemanager.entity.User;
import com.yashwanth.expensemanager.repository.BudgetRepository;
import com.yashwanth.expensemanager.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.YearMonth;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BudgetServiceTest {

    @Mock
    private BudgetRepository budgetRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private BudgetService budgetService;

    @Test
    void createBudget_success() {
        // Arrange
        BudgetRequest request = new BudgetRequest();
        request.setMonth("2026-01");
        request.setAmount(15000.0);

        User user = new User();
        user.setEmail("test@example.com");

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        "test@example.com", null)
        );

        when(userRepository.findByEmail("test@example.com"))
                .thenReturn(Optional.of(user));

        when(budgetRepository.findByUserAndMonth(user, YearMonth.of(2026, 1)))
                .thenReturn(Optional.empty());

        when(budgetRepository.save(any(Budget.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        var response = budgetService.create(request);

        // Assert
        assertEquals(15000.0, response.getAmount());
        assertEquals("2026-01", response.getMonth());
    }

    @Test
    void createBudget_duplicateMonth() {
        // Arrange
        BudgetRequest request = new BudgetRequest();
        request.setMonth("2026-01");
        request.setAmount(10000.0);

        User user = new User();
        user.setEmail("test@example.com");

        Budget existing = new Budget();
        existing.setMonth(YearMonth.of(2026, 1));

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        "test@example.com", null)
        );

        when(userRepository.findByEmail("test@example.com"))
                .thenReturn(Optional.of(user));

        when(budgetRepository.findByUserAndMonth(user, YearMonth.of(2026, 1)))
                .thenReturn(Optional.of(existing));

        // Act & Assert
        assertThrows(RuntimeException.class,
                () -> budgetService.create(request));
    }
}
