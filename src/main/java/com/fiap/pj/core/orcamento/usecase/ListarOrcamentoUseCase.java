package com.fiap.pj.core.orcamento.usecase;

import com.fiap.pj.core.orcamento.adapter.in.api.request.ListarOrcamentoRequest;
import com.fiap.pj.core.orcamento.adapter.in.api.response.OrcamentoResponse;
import com.fiap.pj.infra.sk.api.Slice;

public interface ListarOrcamentoUseCase {

    Slice<OrcamentoResponse> handle(ListarOrcamentoRequest request);
}
