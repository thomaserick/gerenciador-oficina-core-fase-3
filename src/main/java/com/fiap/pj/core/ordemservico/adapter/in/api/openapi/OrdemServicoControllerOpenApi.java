package com.fiap.pj.core.ordemservico.adapter.in.api.openapi;

import com.fiap.pj.core.ordemservico.adapter.in.api.request.ListarOrdemServicoRequest;
import com.fiap.pj.core.ordemservico.adapter.in.api.response.OrdemServicoResponse;
import com.fiap.pj.core.ordemservico.usecase.command.RealizarDiagnosticoOrdemServicoCommand;
import com.fiap.pj.infra.api.Slice;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

public interface OrdemServicoControllerOpenApi {

    @Operation(description = "Retorna uma lista de Ordem de Serviço.", method = "GET")
    Slice<OrdemServicoResponse> listarOrdemServico(@ParameterObject ListarOrdemServicoRequest filterRequest, @ParameterObject Pageable pageable);

    @Operation(description = "Aprovar Orcamento", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Orcamento aprovado com sucesso."),
            @ApiResponse(responseCode = "400", description = "O Orcamento não pode ser aprovado.")})
    ResponseEntity<Void> realizarDiagnosticoOrdemServico(@PathVariable UUID id, @RequestBody RealizarDiagnosticoOrdemServicoCommand cmd);
}
