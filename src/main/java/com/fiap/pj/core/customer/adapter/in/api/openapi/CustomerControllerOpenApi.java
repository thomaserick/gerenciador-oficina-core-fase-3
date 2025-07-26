package com.fiap.pj.core.customer.adapter.in.api.openapi;

import com.fiap.pj.core.customer.usecase.command.CreateCustomerCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface CustomerControllerOpenApi {

    @Operation(description = "Cria um novo Cliente", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Cliente criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "O  Cliente não pode ser criado.")})
    ResponseEntity<Void> createCustomer(@Valid @RequestBody CreateCustomerCommand cmd);


}
