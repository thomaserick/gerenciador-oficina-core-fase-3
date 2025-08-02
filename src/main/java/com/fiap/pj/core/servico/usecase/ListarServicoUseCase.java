package com.fiap.pj.core.servico.usecase;

import com.fiap.pj.core.servico.adapter.in.api.request.ListarServicoRequest;
import com.fiap.pj.core.servico.adapter.in.api.response.ServicoResponse;
import com.fiap.pj.infra.api.Slice;

public interface ListarServicoUseCase {

    Slice<ServicoResponse> handle(ListarServicoRequest request);
}
