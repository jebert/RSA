package com.jebert.rsa.entities.Clients.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jebert.rsa.entities.Clients.model.Client;
import com.jebert.rsa.entities.Clients.service.ClientService;
import com.jebert.rsa.entities.Clients.vo.ClientVo;
import com.jebert.rsa.entities.address.service.AddressService;

import java.util.UUID;

@RestController
@RequestMapping(value = "client")
@Tag(name = "Client", description = "Endpoints to Manage Clientes")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private AddressService addressService;


    @GetMapping(value = "/{uuid}", produces = "application/json")
    @Operation(summary = "Find an Client by UUID", tags = "Client", responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class)) }),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal  Error", responseCode = "500", content = @Content) })
    public ResponseEntity<?> findClientByUUID(@PathVariable UUID uuid) {

        return ResponseEntity.ok(clientService.findClientById(uuid).get());
    }


    @GetMapping( produces = "application/json")
    @Operation(summary = "Find all Saved Client", tags = "Client", responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class)) }),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal  Error", responseCode = "500", content = @Content) })
    public ResponseEntity<?> findAllClientes() {

        return ResponseEntity.ok(clientService.findAllClients());
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @Operation(summary = "Save an Client", tags = "Client", responses = {
            @ApiResponse(description = "Created", responseCode = "201", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class)) }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "403", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal  Error", responseCode = "500", content = @Content) })
    public ResponseEntity<?> saveClient(@RequestBody @Valid ClientVo clientvo) {
 

        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.saveClient(clientService.convertFromClientVo(clientvo)));

    }

    @DeleteMapping(value = "/{uuid}")
    @Operation(summary = "Delete an Client by UUID", tags = "Client", responses = {
            @ApiResponse(description = "No content", responseCode = "203", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "403", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal  Error", responseCode = "500", content = @Content) })
    public ResponseEntity<?> deleteClient(@PathVariable UUID uuid) {

        clientService.delete(uuid);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Client deleted");

    }

    @PutMapping(value = "/{uuid}", consumes = "application/json", produces = "application/json")
    @Operation(summary = "Update an Client", tags = "Client", responses = {
            @ApiResponse(description = "Created", responseCode = "201", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class)) }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "403", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal  Error", responseCode = "500", content = @Content) })
    public ResponseEntity<?> updateClient(
            @PathVariable UUID uuid,
            @RequestBody @Valid ClientVo clientvo) {


        return ResponseEntity.status(HttpStatus.ACCEPTED).body(clientService.saveClient(clientService.convertFromClientVo(clientvo)));

    }

}
