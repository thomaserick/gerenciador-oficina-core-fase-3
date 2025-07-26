package com.fiap.pj.core.usuario.app;


import com.fiap.pj.core.usuario.adapter.out.UserRepositoryJpa;
import com.fiap.pj.core.usuario.domain.User;
import com.fiap.pj.core.usuario.usecase.command.DisablesUserCommand;
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
class DisableUservarServiceTest {

    @Mock
    private UserRepositoryJpa userRepositoryJpa;

    @InjectMocks
    private DisableUserService desativarUsuarioService;

    @Test
    void deveCriarUsuario() {
        var id = UUID.randomUUID();
        when(userRepositoryJpa.findByIdOrThrowNotFound(id)).thenReturn(UserTestFactory.umUsuario());
        desativarUsuarioService.handle(new DisablesUserCommand(id));
        verify(userRepositoryJpa).save(Mockito.any(User.class));

    }
}
