package com.jebert.rsa.entities.permission.controller;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jebert.rsa.entities.permission.model.Permission;
import com.jebert.rsa.entities.permission.model.vo.PermissionVo;
import com.jebert.rsa.entities.permission.service.PermissionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

public class PermissionController {
    @Autowired
    private PermissionService permissionService;


    @GetMapping(value = "/{uuid}", produces = "application/json")
    @Operation(summary = "Find an Permission by UUID", tags = "Permission", responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Permission.class)) }),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal  Error", responseCode = "500", content = @Content) })
    public ResponseEntity<?> findPermissionByUUID(@PathVariable UUID uuid) {

        return ResponseEntity.ok(permissionService.findPermissionById(uuid).get());
    }

    @GetMapping( produces = "application/json")
    @Operation(summary = "Find all Saved Permission", tags = "Permission", responses = {
            @ApiResponse(description = "Success", responseCode = "200",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Permission.class)))
                    }
            ),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal  Error", responseCode = "500", content = @Content) })
    public ResponseEntity<?> findAllPermissions() {

        return ResponseEntity.ok(permissionService.findAllPermissions());
    }



    @PostMapping(consumes = "application/json", produces = "application/json")
    @Operation(summary = "Save an Permission", tags = "Permission", responses = {
            @ApiResponse(description = "Created", responseCode = "201", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Permission.class)) }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "403", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal  Error", responseCode = "500", content = @Content) })
    public ResponseEntity<?> savePermission(@RequestBody @Valid PermissionVo permissionvo) {

        return ResponseEntity.status(HttpStatus.CREATED).body(permissionService.savePermission(null));

    }

    @DeleteMapping(value = "/{uuid}")
    @Operation(summary = "Delete an Permission by UUID", tags = "Permission", responses = {
            @ApiResponse(description = "No content", responseCode = "203", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "403", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal  Error", responseCode = "500", content = @Content) })
    public ResponseEntity<?> deletePermission(@PathVariable UUID uuid) {

        permissionService.delete(uuid);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Permission deleted");

    }

    @PutMapping(value = "/{uuid}", consumes = "application/json", produces = "application/json")
    @Operation(summary = "Update an Permission", tags = "Permission", responses = {
            @ApiResponse(description = "Created", responseCode = "201", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Permission.class)) }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "403", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal  Error", responseCode = "500", content = @Content) })
    public ResponseEntity<?> updatePermission(
            @PathVariable UUID uuid,
            @RequestBody @Valid PermissionVo permissionvo) {
                Permission p = new Permission(permissionvo.permissionName());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(permissionService.savePermission(p));

    }
}
