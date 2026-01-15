package com.yashwanth.expensemanager.entity;

import jakarta.persistence.*;
import java.time.YearMonth;

@Entity
@Table(
        name = "budgets",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "month"})
)
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private User user;

    @Column(nullable = false)
    private YearMonth month;

    @Column(nullable = false)
    private Double amount;

    public Long getId() { return id; }
    public User getUser() { return user; }
    public YearMonth getMonth() { return month; }
    public Double getAmount() { return amount; }

    public void setId(Long id) { this.id = id; }
    public void setUser(User user) { this.user = user; }
    public void setMonth(YearMonth month) { this.month = month; }
    public void setAmount(Double amount) { this.amount = amount; }
}

