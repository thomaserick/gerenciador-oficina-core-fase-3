package com.fiap.pj.core.usuario.app;


import com.fiap.pj.core.usuario.domain.Usuario;
import com.fiap.pj.core.usuario.domain.UsuarioDomainRepository;
import com.fiap.pj.core.usuario.usecase.CriarUsuarioUseCase;
import com.fiap.pj.core.usuario.usecase.command.CriarUsuarioCommand;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class CriarUsuarioService implements CriarUsuarioUseCase {

    private final UsuarioDomainRepository repository;

    @Override
    public Usuario handle(CriarUsuarioCommand cmd) {
        var usuario = new Usuario(UUID.randomUUID(), cmd.getName(), cmd.getLastName(), cmd.getEmail(), cmd.getPassword(), cmd.isActive());
        usuario.adicionarPerfils(cmd.getRoles());
        return repository.save(usuario);
    }
}
