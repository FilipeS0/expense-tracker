package com.expense.tracker.service;

import com.expense.tracker.controller.response.ExpenseResponse;
import com.expense.tracker.mapper.ExpenseMapper;
import com.expense.tracker.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final AuthUserService authUserService;

    public ExpenseService(ExpenseRepository expenseRepository, AuthUserService authUserService) {
        this.expenseRepository = expenseRepository;
        this.authUserService = authUserService;
    }

    public List<ExpenseResponse> getUserLoggedExpenses() {
        Long userId = authUserService.get().getId();

        return expenseRepository.findByAppUser_Id(userId)
                .stream()
                .map(ExpenseMapper::toResponse)
                .toList();
    }

    public List<ExpenseResponse> getUserExpenses(Long id) {
        return expenseRepository.findByAppUser_Id(id)
                .stream()
                .map(ExpenseMapper::toResponse)
                .toList();
    }
}
