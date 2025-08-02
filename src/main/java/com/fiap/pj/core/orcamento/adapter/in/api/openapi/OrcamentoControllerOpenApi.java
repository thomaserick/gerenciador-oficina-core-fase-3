package com.fiap.pj.core.orcamento.adapter.in.api.openapi;

import com.fiap.pj.core.orcamento.usecase.command.CriarOrcamentoCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface OrcamentoControllerOpenApi {

    @Operation(description = "Cria um novo Orcamento", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Orcamento criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "O  Orcamento não pode ser criado.")})
    ResponseEntity<Void> criarOrcamento(@Valid @RequestBody CriarOrcamentoCommand cmd);


}
