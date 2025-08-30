package com.fiap.pj.core.usuario.app.gateways;

import com.fiap.pj.core.usuario.domain.Usuario;

import java.util.UUID;

public interface UsuarioGateway {

    Usuario criar(Usuario usuario);

    void alterar(Usuario usuario);

    void excluir(Usuario usuario);

    Usuario buscarPorIdIdOrThrowNotFound(UUID id);

}
