package com.fiap.pj.core.service.adapter.in.api.openapi;

import com.fiap.pj.core.service.usecase.command.CreateServiceCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface ServiceControllerOpenApi {

    @Operation(description = "Cria um novo servico", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Servico criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "O  Servico não pode ser criado.")})
    ResponseEntity<Void> createService(@Valid @RequestBody CreateServiceCommand cmd);

}
