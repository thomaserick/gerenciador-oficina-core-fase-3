package com.fiap.pj.core.ordemservico.usecase;

import com.fiap.pj.core.ordemservico.adapter.in.api.request.ListarOrdemServicoRequest;
import com.fiap.pj.core.ordemservico.adapter.in.api.response.OrdemServicoResponse;
import com.fiap.pj.infra.api.Slice;

public interface ListarOrdemServicoUseCase {

    Slice<OrdemServicoResponse> handle(ListarOrdemServicoRequest request);

}