package com.fiap.pj.core.cliente.usecase;

import com.fiap.pj.core.cliente.adapter.in.api.request.ListarClienteRequest;
import com.fiap.pj.core.cliente.adapter.in.api.response.ClienteResponse;
import com.fiap.pj.infra.sk.api.Slice;

public interface ListarClienteUseCase {

    Slice<ClienteResponse> handle(ListarClienteRequest request);
}
