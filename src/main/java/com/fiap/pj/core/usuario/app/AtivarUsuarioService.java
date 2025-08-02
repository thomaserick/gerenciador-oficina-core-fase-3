package com.fiap.pj.core.usuario.app;


import com.fiap.pj.core.usuario.domain.UserDomainRepository;
import com.fiap.pj.core.usuario.usecase.AtivarUsuarioUseCase;
import com.fiap.pj.core.usuario.usecase.command.AtivarUsuarioCommand;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class AtivarUsuarioService implements AtivarUsuarioUseCase {

    private final UserDomainRepository repository;

    @Override
    public void handle(AtivarUsuarioCommand cmd) {
        var usuario = repository.findByIdOrThrowNotFound(cmd.id());
        usuario.ativar();
        repository.save(usuario);
    }
}
