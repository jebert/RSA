package com.jebert.rsa.security.controller.vo;

import jakarta.validation.constraints.NotNull;

public record SigninVo(
        @NotNull(message = "Please  enter Username!")String username,
        @NotNull(message = "Please  enter Full Name!")String fullName,
        @NotNull(message = "Please  enter Email!")String email,
        @NotNull(message = "Please enter Password!")String password){
}
