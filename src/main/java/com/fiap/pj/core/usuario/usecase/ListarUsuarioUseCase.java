package com.fiap.pj.core.usuario.usecase;

import com.fiap.pj.infra.usuario.controller.request.ListarUsuarioRequest;
import com.fiap.pj.infra.usuario.controller.response.UsuarioReponse;
import com.fiap.pj.infra.api.Slice;

public interface ListarUsuarioUseCase {

    Slice<UsuarioReponse> handle(ListarUsuarioRequest request);
}
