package com.expense.tracker.controller.request;

import com.expense.tracker.model.Category;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter @Setter
public class ExpenseRequest {

    @NotNull
    @Positive
    BigDecimal amount;

    @NotNull
    Category category;

    @NotNull
    @PastOrPresent(message = "Date cannot be in the future")
    LocalDate expenseDay;
}
