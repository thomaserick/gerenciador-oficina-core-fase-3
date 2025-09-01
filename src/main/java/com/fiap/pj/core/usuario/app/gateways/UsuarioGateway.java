package com.fiap.pj.core.usuario.app.gateways;

import com.fiap.pj.core.usuario.domain.Usuario;
import com.fiap.pj.infra.sk.api.Slice;
import com.fiap.pj.infra.usuario.controller.request.ListarUsuarioRequest;
import com.fiap.pj.infra.usuario.controller.response.UsuarioReponse;

import java.util.UUID;

public interface UsuarioGateway {

    Usuario criar(Usuario usuario);

    void alterar(Usuario usuario);

    void excluir(Usuario usuario);

    Usuario buscarPorIdIdOrThrowNotFound(UUID id);

    Slice<UsuarioReponse> listarUsuarios(ListarUsuarioRequest request);


}
