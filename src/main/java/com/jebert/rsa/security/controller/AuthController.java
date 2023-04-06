package com.jebert.rsa.security.controller;

import com.jebert.rsa.security.controller.vo.LoginVo;
import com.jebert.rsa.security.controller.vo.TokenVo;
import com.jebert.rsa.security.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
@Tag(name = "Authentication EndPoint")
public class AuthController {
    @Autowired
    AuthService authService ;

    @Operation(summary = "Authenticates a user and returns a token", tags = "Authentication EndPoint", responses = {
            @ApiResponse(description = "Created", responseCode = "201", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TokenVo.class)) }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "403", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal  Error", responseCode = "500", content = @Content) })
    @PostMapping(value = "/signin", produces = { "application/json", "application/xml", "application/x-yaml" },
            consumes = { "application/json", "application/xml", "application/x-yaml" })
    public ResponseEntity signin(@RequestBody @Valid LoginVo data) {
        return authService.signin(data);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Generate a new token, using its refreshToken and username information", tags = "Authentication EndPoint", responses = {
            @ApiResponse(description = "Created", responseCode = "201", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TokenVo.class)) }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "403", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal  Error", responseCode = "500", content = @Content) })
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