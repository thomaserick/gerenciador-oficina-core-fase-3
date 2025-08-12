package com.fiap.pj.core.ordemservico.usecase;

import com.fiap.pj.core.ordemservico.adapter.in.api.request.BuscarAcompanhamentoByOrdemServicoIdRequest;
import com.fiap.pj.core.ordemservico.adapter.in.api.response.AcompanhamentoOrdemServicoResponse;

import java.util.Optional;

public interface BuscarAcompanhamentoByOrdemServicoIdUseCase {

    Optional<AcompanhamentoOrdemServicoResponse> handle(BuscarAcompanhamentoByOrdemServicoIdRequest request);

}