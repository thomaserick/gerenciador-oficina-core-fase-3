package com.fiap.pj.core.ordemservico.adapter.in.api.openapi;

import com.fiap.pj.core.ordemservico.adapter.in.api.request.ListarOrdemServicoRequest;
import com.fiap.pj.core.ordemservico.adapter.in.api.response.OrdemServicoResponse;
import com.fiap.pj.infra.api.Slice;
import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;

public interface OrdemServicoControllerOpenApi {

    @Operation(description = "Retorna uma lista de Ordem de Serviço.", method = "GET")
    Slice<OrdemServicoResponse> listarOrdemServico(@ParameterObject ListarOrdemServicoRequest filterRequest, @ParameterObject Pageable pageable);
}
