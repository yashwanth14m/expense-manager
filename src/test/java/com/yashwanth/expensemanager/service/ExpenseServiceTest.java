package com.yashwanth.expensemanager.service;

import com.yashwanth.expensemanager.dto.expense.ExpenseRequest;
import com.yashwanth.expensemanager.entity.Category;
import com.yashwanth.expensemanager.entity.Expense;
import com.yashwanth.expensemanager.repository.CategoryRepository;
import com.yashwanth.expensemanager.repository.ExpenseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExpenseServiceTest {

    @Mock
    private ExpenseRepository expenseRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ExpenseService expenseService;

    @Test
    void createExpense_success() {
        // Arrange
        ExpenseRequest request = new ExpenseRequest();
        request.setAmount(500.0);
        request.setDescription("Dinner");
        request.setDate(LocalDate.now());
        request.setCategoryId(1L);

        Category category = new Category();
        category.setId(1L);
        category.setName("Food");

        when(categoryRepository.findById(1L))
                .thenReturn(Optional.of(category));

        when(expenseRepository.save(any(Expense.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        var response = expenseService.create(request);

        // Assert
        assertNotNull(response);
        assertEquals(500.0, response.getAmount());
        assertEquals("Food", response.getCategory());

        verify(expenseRepository, times(1)).save(any());
    }

    @Test
    void createExpense_categoryNotFound() {
        // Arrange
        ExpenseRequest request = new ExpenseRequest();
        request.setCategoryId(99L);

        when(categoryRepository.findById(99L))
                .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class,
                () -> expenseService.create(request));
    }
}
