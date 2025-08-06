package com.fiap.pj.core.usuario.app;


import com.fiap.pj.core.usuario.domain.Usuario;
import com.fiap.pj.core.usuario.domain.UsuarioDomainRepository;
import com.fiap.pj.core.usuario.usecase.CriarUsuarioUseCase;
import com.fiap.pj.core.usuario.usecase.command.CriarUsuarioCommand;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class CriarUsuarioService implements CriarUsuarioUseCase {

    private final UsuarioDomainRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Usuario handle(CriarUsuarioCommand cmd) {
        var senha = passwordEncoder.encode(cmd.getSenha());
        var usuario = new Usuario(UUID.randomUUID(), cmd.getNome(), cmd.getSobreNome(), cmd.getEmail(), senha, cmd.isAtivo());
        usuario.adicionarPerfils(cmd.getPerfis());
        return repository.save(usuario);
    }
}
