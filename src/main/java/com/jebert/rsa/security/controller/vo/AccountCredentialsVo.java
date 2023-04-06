package com.jebert.rsa.security.controller.vo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record AccountCredentialsVo(
        @NotNull(message = "Please enter Username!")String username,
        @NotNull(message = "Please enter Password!")String password){
}
