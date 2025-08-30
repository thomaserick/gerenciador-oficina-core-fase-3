package com.fiap.pj.core.usuario.app.usecase;


import com.fiap.pj.core.usuario.app.gateways.UsuarioGateway;
import com.fiap.pj.core.usuario.app.usecase.command.AlterarUsuarioCommand;


public class AlterarUsuarioUseCaseImpl implements AlterarUsuarioUseCase {

    private final UsuarioGateway usuarioGateway;

    public AlterarUsuarioUseCaseImpl(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public void handle(AlterarUsuarioCommand cmd) {
        var usuario = usuarioGateway.buscarPorIdIdOrThrowNotFound(cmd.getId());
        usuario.alterar(cmd.getNome(), cmd.getSobreNome(), cmd.isAtivo(), cmd.getSenha(), cmd.getPerfis());
        usuarioGateway.alterar(usuario);
    }
}
