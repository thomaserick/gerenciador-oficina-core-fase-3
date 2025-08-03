package com.fiap.pj.core.cliente.adapter.in.api.openapi;

import com.fiap.pj.core.sk.web.ResponseEntityUtils.ResponseId;
import com.fiap.pj.core.veiculo.usecase.command.AdicionarVeiculoClienteCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

public interface ClienteVeiculoControllerOpenApi {


    @Operation(description = "Adicionar veiculo ao Cliente", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Veículo adicionado com sucesso."),
            @ApiResponse(responseCode = "400", description = "O  Veículo não pode ser adicionado ao cliente.")})
    ResponseEntity<ResponseId> adicionarVeiculo(@PathVariable UUID id, @RequestBody AdicionarVeiculoClienteCommand cmd);

}
