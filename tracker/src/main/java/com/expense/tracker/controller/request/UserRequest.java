package com.expense.tracker.controller.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class UserRequest {

    @NotBlank
    @Size(min = 3, max = 255)
    private String name;

    @NotNull @Email
    private String email;

    @NotBlank
    private String password;

    @NotNull
    private LocalDate birthday;
}
