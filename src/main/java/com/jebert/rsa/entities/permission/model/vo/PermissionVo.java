package com.jebert.rsa.entities.permission.model.vo;

import jakarta.validation.constraints.NotNull;

public record PermissionVo(@NotNull(message = "Please enter Permission!") String permissionName) {
}