package com.expense.tracker.controller.response;

import com.expense.tracker.model.Category;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExpenseResponse {

    private Long id;
    private Long userId;
    private BigDecimal amount;
    private Category category;
    private LocalDate expenseDay;

}
