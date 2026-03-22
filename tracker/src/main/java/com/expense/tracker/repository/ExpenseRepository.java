package com.expense.tracker.repository;

import com.expense.tracker.domain.Expense;
import com.expense.tracker.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByAppUser_Id(Long appUserId);

    List<Expense> findByExpenseDay(LocalDate expenseDay);

    List<Expense> findByAppUser_IdAndExpenseDayBetween(Long appUserId, LocalDate start, LocalDate end);

    List<Expense> findByAppUser_IdAndCategory(Long appUserId, Category category);
}
