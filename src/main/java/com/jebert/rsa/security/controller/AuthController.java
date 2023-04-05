package com.jebert.rsa.security.controller;

import com.jebert.rsa.security.controller.vo.AccountCredentialsVo;
import com.jebert.rsa.security.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
@Tag(name = "Authentication EndPoint")
public class AuthController {
    @Autowired
    AuthService authService ;
    @Operation(summary = "Authenticates a user and returns a token")
    @SuppressWarnings("rawtypes")
    @PostMapping(value = "/signin", produces = { "application/json", "application/xml", "application/x-yaml" },
            consumes = { "application/json", "application/xml", "application/x-yaml" })
    public ResponseEntity signin(@RequestBody @Valid AccountCredentialsVo data) {

        return authService.signin(data);

    }

}