package com.fiap.pj.core.usuario.app;


import com.fiap.pj.core.usuario.domain.User;
import com.fiap.pj.core.usuario.domain.UserDomainRepository;
import com.fiap.pj.core.usuario.usecase.CreateUserUseCase;
import com.fiap.pj.core.usuario.usecase.command.CreateUserCommand;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class CreateUserService implements CreateUserUseCase {

    private final UserDomainRepository repository;

    @Override
    public User handle(CreateUserCommand cmd) {
        var usuario = new User(UUID.randomUUID(), cmd.getNome(), cmd.getSobreNome(), cmd.getEmail(), cmd.getSenha(), cmd.isAtivo());
        usuario.addRoles(cmd.getPerfis());
        return repository.save(usuario);
    }
}
