package com.expense.tracker.mapper;

import com.expense.tracker.controller.request.ExpenseRequest;
import com.expense.tracker.controller.response.ExpenseResponse;
import com.expense.tracker.domain.AppUser;
import com.expense.tracker.domain.Expense;

import java.util.List;


public class ExpenseMapper {

    public static ExpenseResponse toResponse(Expense expense) {

        return ExpenseResponse.builder()
                .id(expense.getId())
                .userId(expense.getAppUser().getId())
                .amount(expense.getAmount())
                .expenseDay(expense.getExpenseDay())
                .category(expense.getCategory())
                .build();
    }

    public static List<ExpenseResponse> toResponseList(List<Expense> expenses) {
        return expenses.stream()
                .map(ExpenseMapper::toResponse)
                .toList();
    }

    public static Expense toEntity(ExpenseRequest request, AppUser user) {
        Expense entity = new Expense();

        entity.setExpenseDay(request.getExpenseDay());
        entity.setCategory(request.getCategory());
        entity.setAmount(request.getAmount());
        entity.setAppUser(user);
        return entity;
    }
}
