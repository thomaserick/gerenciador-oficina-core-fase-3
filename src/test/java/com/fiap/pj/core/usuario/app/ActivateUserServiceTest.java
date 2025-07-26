package com.fiap.pj.core.usuario.app;


import com.fiap.pj.core.usuario.adapter.out.UserRepositoryJpa;
import com.fiap.pj.core.usuario.domain.User;
import com.fiap.pj.core.usuario.usecase.command.ActivateUserCommand;
import com.fiap.pj.core.usuario.util.factrory.UserTestFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ActivateUserServiceTest {

    @Mock
    private UserRepositoryJpa userRepositoryJpa;

    @InjectMocks
    private ActivateUserService activateUserService;

    @Test
    void shouldActivateUSer() {
        var id = UUID.randomUUID();
        when(userRepositoryJpa.findByIdOrThrowNotFound(id)).thenReturn(UserTestFactory.oneUser());
        activateUserService.handle(new ActivateUserCommand(id));
        verify(userRepositoryJpa).save(Mockito.any(User.class));
    }
}
