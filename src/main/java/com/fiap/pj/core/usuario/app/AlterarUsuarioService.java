package com.fiap.pj.core.usuario.app;


import com.fiap.pj.core.usuario.domain.UsuarioDomainRepository;
import com.fiap.pj.core.usuario.usecase.AlterarUsuarioUseCase;
import com.fiap.pj.core.usuario.usecase.command.AlterarUsuarioCommand;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class AlterarUsuarioService implements AlterarUsuarioUseCase {

    private final UsuarioDomainRepository repository;

    @Override
    public void handle(AlterarUsuarioCommand cmd) {
        var usuario = repository.findByIdOrThrowNotFound(cmd.getId());
        usuario.alterar(cmd.getName(), cmd.getLastName(), cmd.isActive(), cmd.getPassword(), cmd.getRoles());
        repository.save(usuario);
    }
}
