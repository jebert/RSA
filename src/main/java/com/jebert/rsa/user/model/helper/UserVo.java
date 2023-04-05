package com.jebert.rsa.user.model.helper;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UserVo(UUID id,
                     @NotNull(message = "Please enter Username!") String userName,
                     @NotNull(message = "Please enter fullName!")String fullName,
                     @NotNull(message = "Please enter password!")String password,
                     @NotNull(message = "Please enter a e-mail!") @Email String email,
                     @NotNull(message = "Please define - Account isn´t Expired?")Boolean accountNonExpired,
                     @NotNull(message = "Please define - Account isn´t Locked?")Boolean accountNonLocked,
                     @NotNull(message = "Please define - Credentials aren´t Expired?")Boolean credentialsNonExpired,
                     @NotNull(message = "Please define - Account is enabled?")Boolean enabled) {

}
