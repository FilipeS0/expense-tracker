package com.expense.tracker.service;

import com.expense.tracker.controller.request.ExpenseRequest;
import com.expense.tracker.controller.response.ExpenseResponse;
import com.expense.tracker.domain.AppUser;
import com.expense.tracker.domain.Expense;
import com.expense.tracker.mapper.ExpenseMapper;
import com.expense.tracker.repository.ExpenseRepository;
import org.springframework.stereotype.Service;


import static com.expense.tracker.mapper.ExpenseMapper.toEntity;

@Service
public class IncludesExpenseService {

    private final ExpenseRepository expenseRepository;
    private final AuthUserService authUserService;

    public IncludesExpenseService(ExpenseRepository expenseRepository, AuthUserService authUserService) {
        this.expenseRepository = expenseRepository;
        this.authUserService = authUserService;
    }

    public ExpenseResponse includesExpense(ExpenseRequest request) {
        AppUser user = authUserService.get();

        Expense expense = toEntity(request, user);

        expenseRepository.save(expense);

        return ExpenseMapper.toResponse(expense);
    }
}
