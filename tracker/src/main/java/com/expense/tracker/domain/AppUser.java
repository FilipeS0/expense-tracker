package com.expense.tracker.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "app_user")
@Getter @Setter
@EqualsAndHashCode(of = "id") @ToString(of = "id")
public class AppUser {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private LocalDate birthday;

    @OneToMany(mappedBy = "appUser")
    private List<Expense> expenses;
}
