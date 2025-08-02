package com.fiap.pj.core.usuario.app;


import com.fiap.pj.core.usuario.adapter.out.db.UsuarioRepositoryJpa;
import com.fiap.pj.core.usuario.domain.Usuario;
import com.fiap.pj.core.usuario.util.factrory.UserTestFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.fiap.pj.core.usuario.util.factrory.UserTestFactory.E_MAIL;
import static com.fiap.pj.core.usuario.util.factrory.UserTestFactory.ID;
import static com.fiap.pj.core.usuario.util.factrory.UserTestFactory.LAST_NAME;
import static com.fiap.pj.core.usuario.util.factrory.UserTestFactory.NAME;
import static com.fiap.pj.core.usuario.util.factrory.UserTestFactory.PASSWORD;
import static com.fiap.pj.core.usuario.util.factrory.UserTestFactory.USER_ROLE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CriarUsuarioServiceTest {

    @Mock
    private UsuarioRepositoryJpa usuarioRepositoryJpa;

    @InjectMocks
    private CriarUsuarioService criarUsuarioService;

    @Test
    void deveCriarUsuarior() {
        when(usuarioRepositoryJpa.save(any(Usuario.class))).thenReturn(UserTestFactory.oneUser());

        var usuario = criarUsuarioService.handle(UserTestFactory.oneCreateUserCommand());

        assertNotNull(usuario);
        assertEquals(ID, usuario.getId());
        assertEquals(NAME, usuario.getNome());
        assertEquals(LAST_NAME, usuario.getSobreNome());
        assertEquals(E_MAIL, usuario.getEmail());
        assertEquals(PASSWORD, usuario.getSenha());
        assertTrue(usuario.getPerfis().contains(USER_ROLE));
    }
}
