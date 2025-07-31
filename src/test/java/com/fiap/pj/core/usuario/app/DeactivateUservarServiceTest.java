package com.fiap.pj.core.usuario.app;


import com.fiap.pj.core.usuario.adapter.out.db.UserRepositoryJpa;
import com.fiap.pj.core.usuario.domain.User;
import com.fiap.pj.core.usuario.usecase.command.DeactivateUserCommand;
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
class DeactivateUservarServiceTest {

    @Mock
    private UserRepositoryJpa userRepositoryJpa;

    @InjectMocks
    private DeactivateUserService desativarUsuarioService;

    @Test
    void shouldDeactivateUser() {
        var id = UUID.randomUUID();
        when(userRepositoryJpa.findByIdOrThrowNotFound(id)).thenReturn(UserTestFactory.oneUser());
        desativarUsuarioService.handle(new DeactivateUserCommand(id));
        verify(userRepositoryJpa).save(Mockito.any(User.class));

    }
}
