package com.fiap.pj.core.usuario.app;


import com.fiap.pj.core.usuario.domain.UserDomainRepository;
import com.fiap.pj.core.usuario.usecase.InativarUsuarioUseCase;
import com.fiap.pj.core.usuario.usecase.command.InativarUsuarioCommand;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class InativarUsuarioService implements InativarUsuarioUseCase {

    private final UserDomainRepository repository;

    @Override
    public void handle(InativarUsuarioCommand cmd) {
        var usuario = repository.findByIdOrThrowNotFound(cmd.id());
        usuario.inativar();
        repository.save(usuario);
    }
}
