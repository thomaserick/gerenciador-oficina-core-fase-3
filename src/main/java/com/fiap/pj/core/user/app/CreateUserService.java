package com.fiap.pj.core.user.app;


import com.fiap.pj.core.user.domain.User;
import com.fiap.pj.core.user.domain.UserDomainRepository;
import com.fiap.pj.core.user.usecase.CreateUserUseCase;
import com.fiap.pj.core.user.usecase.command.CreateUserCommand;
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
        var usuario = new User(UUID.randomUUID(), cmd.getName(), cmd.getLastName(), cmd.getEmail(), cmd.getPassword(), cmd.isActive());
        usuario.addRoles(cmd.getRoles());
        return repository.save(usuario);
    }
}
