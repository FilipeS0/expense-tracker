package com.expense.tracker.domain;

import com.expense.tracker.model.Category;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "expense")
@Getter @Setter
@EqualsAndHashCode(of = "id") @ToString(of = "id")
public class Expense {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private Category category;

    private LocalDate expenseDay;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;
}
