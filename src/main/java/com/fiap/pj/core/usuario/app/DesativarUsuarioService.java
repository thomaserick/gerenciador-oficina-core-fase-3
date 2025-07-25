package com.fiap.pj.core.usuario.app;


import com.fiap.pj.core.usuario.domain.UsuarioDomainRepository;
import com.fiap.pj.core.usuario.usecase.DesativarUsuarioUseCase;
import com.fiap.pj.core.usuario.usecase.command.DesativarUsuarioCommand;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class DesativarUsuarioService implements DesativarUsuarioUseCase {

    private final UsuarioDomainRepository repository;

    @Override
    public void handle(DesativarUsuarioCommand cmd) {
        var usuario = repository.findByIdOrThrowNotFound(cmd.id());
        usuario.desativar();
        repository.save(usuario);
    }
}
