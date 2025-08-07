package com.fiap.pj.core.usuario.app;


import com.fiap.pj.core.usuario.adapter.out.db.UsuarioRepositoryJpa;
import com.fiap.pj.core.usuario.domain.Usuario;
import com.fiap.pj.core.usuario.usecase.command.AtivarUsuarioCommand;
import com.fiap.pj.core.usuario.util.factrory.UsuarioTestFactory;
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
class AtivarUsuarioServiceTest {

    @Mock
    private UsuarioRepositoryJpa usuarioRepositoryJpa;

    @InjectMocks
    private AtivarUsuarioService ativarUsuarioService;

    @Test
    void deveAtivarUsuario() {
        var id = UUID.randomUUID();
        when(usuarioRepositoryJpa.findByIdOrThrowNotFound(id)).thenReturn(UsuarioTestFactory.umUsuario());
        ativarUsuarioService.handle(new AtivarUsuarioCommand(id));
        verify(usuarioRepositoryJpa).save(Mockito.any(Usuario.class));
    }
}
