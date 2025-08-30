package com.fiap.pj.core.usuario.app.usecase;


import com.fiap.pj.core.usuario.app.gateways.UsuarioGateway;
import com.fiap.pj.core.usuario.app.usecase.command.ExcluirUsuarioCommand;
import com.fiap.pj.core.usuario.exception.UsuarioExceptions.UsuarioComRelacionamentoException;
import org.springframework.dao.DataIntegrityViolationException;


public class ExcluirUsuarioUseCaseImpl implements ExcluirUsuarioUseCase {

    private final UsuarioGateway usuarioGateway;

    public ExcluirUsuarioUseCaseImpl(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public void handle(ExcluirUsuarioCommand cmd) {
        var usuario = usuarioGateway.buscarPorIdIdOrThrowNotFound(cmd.id());
        try {
            usuarioGateway.excluir(usuario);
        } catch (DataIntegrityViolationException e) {
            throw new UsuarioComRelacionamentoException();
        }
    }
}
