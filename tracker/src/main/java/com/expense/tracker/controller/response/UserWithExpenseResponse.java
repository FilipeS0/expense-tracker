package com.expense.tracker.controller.response;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserWithExpenseResponse {

    private Long id;
    private String name;
    private List<ExpenseResponse> expenses;
}
