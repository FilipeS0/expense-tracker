package com.expense.tracker.controller.response;

import lombok.Getter;

public record ErrorResponse(String error, String message) {
}
