package com.expense.tracker.mapper;

import com.expense.tracker.controller.request.UserRequest;
import com.expense.tracker.controller.response.UserResponse;
import com.expense.tracker.domain.AppUser;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public static AppUser toEntity(UserRequest request) {
        AppUser entity = new AppUser();
        entity.setEmail(request.getEmail());
        entity.setPassword(request.getPassword());
        entity.setName(request.getName());
        entity.setBirthday(request.getBirthday());
        return entity;
    }

    public static UserResponse toResponse(AppUser entity) {
        return UserResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .birthday(entity.getBirthday())
                .build();
    }

}
