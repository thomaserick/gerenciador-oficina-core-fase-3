package com.fiap.pj.core.usuario.app;


import com.fiap.pj.core.usuario.usecase.ExcluirUsuarioUseCase;
import com.fiap.pj.core.usuario.usecase.command.ExcluirUsuarioCommand;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class ExcluirUsuarioUseCaseImpl implements ExcluirUsuarioUseCase {


    @Override
    public void handle(ExcluirUsuarioCommand cmd) {
//        var usuario = repository.findByIdOrThrowNotFound(cmd.id());
//        repository.delete(usuario);
    }
}
