package com.fiap.pj.core.usuario.app;


import com.fiap.pj.core.usuario.domain.Usuario;
import com.fiap.pj.core.usuario.domain.UsuarioDomainRepository;
import com.fiap.pj.core.usuario.usecase.CriarUsuarioUseCase;
import com.fiap.pj.core.usuario.usecase.command.UsuarioCommand;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class UsuarioService implements CriarUsuarioUseCase {

    private final UsuarioDomainRepository repository;

    @Override
    public Usuario handle(UsuarioCommand cmd) {
        var usuario = new Usuario(UUID.randomUUID(), cmd.nome(), cmd.sobreNome(), cmd.email(), cmd.senha(), cmd.ativo());
        usuario.addPerfil(cmd.perfis());
        return repository.save(usuario);

    }
}
