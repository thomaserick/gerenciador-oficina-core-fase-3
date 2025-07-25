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
        var usuario = new Usuario(UUID.randomUUID(), cmd.getNome(), cmd.getSobreNome(), cmd.getEmail(), cmd.getSenha(), cmd.isAtivo());
        usuario.addPerfil(cmd.getPerfis());
        return repository.save(usuario);
    }
}
