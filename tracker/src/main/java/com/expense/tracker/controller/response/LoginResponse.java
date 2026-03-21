package com.expense.tracker.controller.response;

public record LoginResponse(String accessToken, Long expiresIn) {
}
