package com.fiap.pj.core.user.app;


import com.fiap.pj.core.user.domain.UserDomainRepository;
import com.fiap.pj.core.user.usecase.ActivateUserUseCase;
import com.fiap.pj.core.user.usecase.command.ActivateUserCommand;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class ActivateUserService implements ActivateUserUseCase {

    private final UserDomainRepository repository;

    @Override
    public void handle(ActivateUserCommand cmd) {
        var user = repository.findByIdOrThrowNotFound(cmd.id());
        user.activate();
        repository.save(user);
    }
}
