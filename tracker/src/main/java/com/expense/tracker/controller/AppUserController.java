package com.expense.tracker.controller;

import com.expense.tracker.controller.request.ExpenseRequest;
import com.expense.tracker.controller.request.UserRequest;
import com.expense.tracker.controller.response.ExpenseResponse;
import com.expense.tracker.controller.response.UserResponse;
import com.expense.tracker.service.ExpenseService;
import com.expense.tracker.service.IncludesExpenseService;
import com.expense.tracker.service.UserService;
import com.expense.tracker.service.IncludesUserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class AppUserController {

    private final UserService getUserService;
    private final IncludesUserService includesUserService;
    private final ExpenseService expenseService;
    private final IncludesExpenseService includesExpenseService;

    public AppUserController(UserService buscarUsuarioService, IncludesUserService includesUserService, ExpenseService expenseService, IncludesExpenseService includesExpenseService) {
        this.getUserService = buscarUsuarioService;
        this.includesUserService = includesUserService;
        this.expenseService = expenseService;
        this.includesExpenseService = includesExpenseService;
    }

    @PostMapping
    public UserResponse includes(@Valid @RequestBody UserRequest request) {
        return includesUserService.includes(request);
    }

    @GetMapping("/me")
    public UserResponse getUser() {
        return getUserService.getUser();
    }

    @GetMapping("/me/expenses")
    public List<ExpenseResponse> getUserExpenses() {
        return expenseService.getUserLoggedExpenses();
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return getUserService.getUserById(id);
    }

    @GetMapping("/{id}/expenses")
    public List<ExpenseResponse> getUserExpensesById(@PathVariable Long id) {
        return expenseService.getUserExpenses(id);
    }

    @PostMapping("/me/expenses")
    public ExpenseResponse includesExpense(@RequestBody @Valid ExpenseRequest request) {
        return includesExpenseService.includesExpense(request);
    }
}
