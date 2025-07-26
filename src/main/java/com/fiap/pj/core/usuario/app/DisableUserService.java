package com.fiap.pj.core.usuario.app;


import com.fiap.pj.core.usuario.domain.UserDomainRepository;
import com.fiap.pj.core.usuario.usecase.DisableUserUseCase;
import com.fiap.pj.core.usuario.usecase.command.DisablesUserCommand;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class DisableUserService implements DisableUserUseCase {

    private final UserDomainRepository repository;

    @Override
    public void handle(DisablesUserCommand cmd) {
        var user = repository.findByIdOrThrowNotFound(cmd.id());
        user.disable();
        repository.save(user);
    }
}
