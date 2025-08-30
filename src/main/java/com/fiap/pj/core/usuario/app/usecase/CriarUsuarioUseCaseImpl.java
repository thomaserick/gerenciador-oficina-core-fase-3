package com.fiap.pj.core.usuario.app.usecase;


import com.fiap.pj.core.usuario.app.gateways.UsuarioGateway;
import com.fiap.pj.core.usuario.domain.Usuario;
import com.fiap.pj.core.usuario.usecase.command.CriarUsuarioCommand;

import java.util.UUID;


public class CriarUsuarioUseCaseImpl implements CriarUsuarioUseCase {

    private final UsuarioGateway usuarioGateway;


    public CriarUsuarioUseCaseImpl(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public UUID handle(CriarUsuarioCommand cmd) {
        var usuario = new Usuario(UUID.randomUUID(), cmd.getNome(), cmd.getSobreNome(), cmd.getEmail(), cmd.getSenha(), cmd.isAtivo());
        usuario.adicionarPerfils(cmd.getPerfis());
        usuarioGateway.criarUsuario(usuario);
        return usuario.getId();
    }
}
