package com.fiap.pj.core.customer.adapter.in.api.openapi;

import com.fiap.pj.core.customer.usecase.command.CreateCustomerCommand;
import com.fiap.pj.core.customer.usecase.command.UpdateCustomerCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

public interface CustomerControllerOpenApi {

    @Operation(description = "Cria um novo Cliente", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Cliente criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "O  Cliente não pode ser criado.")})
    ResponseEntity<Void> createCustomer(@Valid @RequestBody CreateCustomerCommand cmd);

    @Operation(description = "Alterar um  Cliente", method = "PUT")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Cliente alterado com sucesso."),
            @ApiResponse(responseCode = "400", description = "O  Cliente não pode ser alterado.")})
    ResponseEntity<Void> updateCustomer(@Valid @PathVariable UUID id, @RequestBody UpdateCustomerCommand cmd);


    @Operation(description = "Desativar um Cliente", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Cliente desativado com sucesso."),
            @ApiResponse(responseCode = "400", description = "O  Cliente não pode ser desativado.")})
    ResponseEntity<Void> deactivateCustomer(@PathVariable UUID id);

    @Operation(description = "Ativar um Cliente", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Cliente Ativado com sucesso."),
            @ApiResponse(responseCode = "400", description = "O  Cliente não pode ser ativado.")})
    ResponseEntity<Void> activateCustomer(@PathVariable UUID id);

}
