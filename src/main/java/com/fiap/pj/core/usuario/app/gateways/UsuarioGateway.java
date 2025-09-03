package com.fiap.pj.core.usuario.app.gateways;

import com.fiap.pj.core.usuario.domain.Usuario;
import com.fiap.pj.infra.sk.api.Slice;
import com.fiap.pj.infra.usuario.controller.request.ListarUsuarioRequest;
import com.fiap.pj.infra.usuario.controller.response.UsuarioReponse;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioGateway {

    Usuario salvar(Usuario usuario);

    void alterar(Usuario usuario);

    void excluir(Usuario usuario);

    Optional<Usuario> buscarPorId(UUID id);

    Slice<UsuarioReponse> listarUsuarios(ListarUsuarioRequest request);
}
