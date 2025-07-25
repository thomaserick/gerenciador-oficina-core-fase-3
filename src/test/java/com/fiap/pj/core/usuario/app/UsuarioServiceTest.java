package com.fiap.pj.core.usuario.app;


import com.fiap.pj.core.usuario.adapter.out.UsuarioRepositoryJpa;
import com.fiap.pj.core.usuario.domain.Usuario;
import com.fiap.pj.core.usuario.util.factrory.UsuarioTestFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.fiap.pj.core.usuario.util.factrory.UsuarioTestFactory.E_MAIL;
import static com.fiap.pj.core.usuario.util.factrory.UsuarioTestFactory.ID;
import static com.fiap.pj.core.usuario.util.factrory.UsuarioTestFactory.NOME;
import static com.fiap.pj.core.usuario.util.factrory.UsuarioTestFactory.PERFIL_USUARIO;
import static com.fiap.pj.core.usuario.util.factrory.UsuarioTestFactory.SENHA;
import static com.fiap.pj.core.usuario.util.factrory.UsuarioTestFactory.SOBRE_NOME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepositoryJpa usuarioRepositoryJpa;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void deveCriarUsuario() {
        when(usuarioRepositoryJpa.save(any(Usuario.class))).thenReturn(UsuarioTestFactory.umUsuario());

        var usuario = usuarioService.handle(UsuarioTestFactory.umUsuarioCommand());

        assertNotNull(usuario);
        assertEquals(ID, usuario.getId());
        assertEquals(NOME, usuario.getNome());
        assertEquals(SOBRE_NOME, usuario.getSobreNome());
        assertEquals(E_MAIL, usuario.getEmail());
        assertEquals(SENHA, usuario.getSenha());
        assertTrue(usuario.getPerfis().contains(PERFIL_USUARIO));
    }
}
