package com.fiap.pj.core.usuario.app;


import com.fiap.pj.core.usuario.app.usecase.CriarUsuarioUseCaseImpl;
import com.fiap.pj.core.usuario.domain.Usuario;
import com.fiap.pj.core.usuario.util.factrory.UsuarioTestFactory;
import com.fiap.pj.infra.usuario.persistence.UsuarioRepositoryJpa;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.fiap.pj.core.usuario.util.factrory.UsuarioTestFactory.E_MAIL;
import static com.fiap.pj.core.usuario.util.factrory.UsuarioTestFactory.ID;
import static com.fiap.pj.core.usuario.util.factrory.UsuarioTestFactory.LAST_NAME;
import static com.fiap.pj.core.usuario.util.factrory.UsuarioTestFactory.NAME;
import static com.fiap.pj.core.usuario.util.factrory.UsuarioTestFactory.PASSWORD;
import static com.fiap.pj.core.usuario.util.factrory.UsuarioTestFactory.USER_ROLE;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CriarUsuarioUseCaseImplTest {

    @Mock
    private UsuarioRepositoryJpa usuarioRepositoryJpa;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CriarUsuarioUseCaseImpl criarUsuarioUseCaseImpl;

    @Test
    void deveCriarUsuarior() {
        when(passwordEncoder.encode(anyString())).thenReturn("1234");
        when(usuarioRepositoryJpa.save(any(Usuario.class))).thenReturn(UsuarioTestFactory.umUsuario());

        var usuario = criarUsuarioUseCaseImpl.handle(UsuarioTestFactory.umCriarUsuarioCommand());

        assertNotNull(usuario);
        assertEquals(ID, usuario.getId());
        assertEquals(NAME, usuario.getNome());
        assertEquals(LAST_NAME, usuario.getSobreNome());
        assertEquals(E_MAIL, usuario.getEmail());
        assertEquals(PASSWORD, usuario.getSenha());
        assertTrue(usuario.getPerfis().contains(USER_ROLE));
    }
}
