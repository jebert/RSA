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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
@Tag(name = "Authentication EndPoint")
public class AuthController {
    @Autowired
    AuthService authService ;
    @Operation(summary = "Authenticates a user and returns a token")
    @PostMapping(value = "/signin", produces = { "application/json", "application/xml", "application/x-yaml" },
            consumes = { "application/json", "application/xml", "application/x-yaml" })
    public ResponseEntity signin(@RequestBody @Valid AccountCredentialsVo data) {
        return authService.signin(data);
    }

    @Operation(summary = "Authenticates a user and returns a token")
    @PutMapping (value = "/refresh/{username}", produces = { "application/json", "application/xml", "application/x-yaml" },
            consumes = { "application/json", "application/xml", "application/x-yaml" })
    public ResponseEntity refresh(@PathVariable("username") String username,
                                  @RequestHeader("Authorization") String token) {
        if (checkIfParamsNotNull(username, token)) {
            ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Client Request!");
        }
        var tokenResponse = authService.refreshToken(username, token);
        if (tokenResponse == null) {
            ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Client Request!");
        }
        return tokenResponse;
    }

    private static boolean checkIfParamsNotNull(String username, String token) {
        return username == null || username.isBlank() || token == null || token.isBlank();
    }

}