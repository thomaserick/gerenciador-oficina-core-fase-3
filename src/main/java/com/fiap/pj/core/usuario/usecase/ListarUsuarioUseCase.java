package com.fiap.pj.core.usuario.usecase;

import com.fiap.pj.core.usuario.adapter.in.api.request.ListarUsuarioRequest;
import com.fiap.pj.core.usuario.adapter.in.api.response.UsuarioReponse;
import com.fiap.pj.infra.api.Slice;

public interface ListarUsuarioUseCase {

    Slice<UsuarioReponse> handle(ListarUsuarioRequest request);
}
