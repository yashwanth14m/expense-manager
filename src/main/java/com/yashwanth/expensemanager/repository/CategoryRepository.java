package com.yashwanth.expensemanager.repository;

import com.yashwanth.expensemanager.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
