package com.fiap.pj.core.usuario.app;


import com.fiap.pj.core.usuario.domain.UserDomainRepository;
import com.fiap.pj.core.usuario.usecase.DeactivateUserUseCase;
import com.fiap.pj.core.usuario.usecase.command.DeactivateUserCommand;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class DeactivateUserService implements DeactivateUserUseCase {

    private final UserDomainRepository repository;

    @Override
    public void handle(DeactivateUserCommand cmd) {
        var user = repository.findByIdOrThrowNotFound(cmd.id());
        user.deactivate();
        repository.save(user);
    }
}
