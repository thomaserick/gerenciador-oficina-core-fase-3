package com.fiap.pj.core.usuario.app;


import com.fiap.pj.core.usuario.adapter.out.db.UsuarioRepositoryJpa;
import com.fiap.pj.core.usuario.domain.Usuario;
import com.fiap.pj.core.usuario.usecase.command.InativarUsuarioCommand;
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
class InativarUsuarioServiceTest {

    @Mock
    private UsuarioRepositoryJpa usuarioRepositoryJpa;

    @InjectMocks
    private InativarUsuarioService desativarUsuarioService;

    @Test
    void deveInativarUsuario() {
        var id = UUID.randomUUID();
        when(usuarioRepositoryJpa.findByIdOrThrowNotFound(id)).thenReturn(UserTestFactory.oneUser());
        desativarUsuarioService.handle(new InativarUsuarioCommand(id));
        verify(usuarioRepositoryJpa).save(Mockito.any(Usuario.class));

    }
}
