package com.expense.tracker.mapper;

import com.expense.tracker.controller.response.UserWithExpenseResponse;
import com.expense.tracker.domain.AppUser;

import java.util.List;

public class UserWithExpenseMapper {

    public static UserWithExpenseResponse toResponseUserWithExpense(AppUser entity) {
        return UserWithExpenseResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .expenses(
                        entity.getExpenses() != null
                                ? ExpenseMapper.toResponseList(entity.getExpenses())
                                : List.of())
                .build();
    }

}
