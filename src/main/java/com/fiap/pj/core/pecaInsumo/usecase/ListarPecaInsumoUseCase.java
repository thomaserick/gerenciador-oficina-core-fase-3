package com.fiap.pj.core.pecaInsumo.usecase;

import com.fiap.pj.core.pecaInsumo.adapter.in.api.request.ListarPecaInsumoRequest;
import com.fiap.pj.core.pecaInsumo.adapter.in.api.response.PecaInsumoResponse;
import com.fiap.pj.infra.api.Slice;

public interface ListarPecaInsumoUseCase {

    Slice<PecaInsumoResponse> handle(ListarPecaInsumoRequest filterRequest);
} 