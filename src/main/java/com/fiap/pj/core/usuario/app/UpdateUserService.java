package com.fiap.pj.core.usuario.app;


import com.fiap.pj.core.usuario.domain.UserDomainRepository;
import com.fiap.pj.core.usuario.usecase.UpdateUserUseCase;
import com.fiap.pj.core.usuario.usecase.command.UpdateUserCommand;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class UpdateUserService implements UpdateUserUseCase {

    private final UserDomainRepository repository;

    @Override
    public void handle(UpdateUserCommand cmd) {
        var user = repository.findByIdOrThrowNotFound(cmd.getId());
        user.update(cmd.getName(), cmd.getLastName(), cmd.isActive(), cmd.getPassword(), cmd.getRoles());
        repository.save(user);
    }
}
