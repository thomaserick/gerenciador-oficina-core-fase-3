package com.fiap.pj.core.pecainsumo.usecase;

import com.fiap.pj.core.pecainsumo.adapter.in.api.request.ListarPecaInsumoRequest;
import com.fiap.pj.core.pecainsumo.adapter.in.api.response.PecaInsumoResponse;
import com.fiap.pj.infra.sk.api.Slice;

public interface ListarPecaInsumoUseCase {

    Slice<PecaInsumoResponse> handle(ListarPecaInsumoRequest filterRequest);
} 