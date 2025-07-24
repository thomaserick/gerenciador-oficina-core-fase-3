package com.fiap.pj.core.usuario.domain;

import com.fiap.pj.core.usuario.util.factrory.UsuarioTestFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.fiap.pj.core.usuario.util.factrory.UsuarioTestFactory.E_MAIL;
import static com.fiap.pj.core.usuario.util.factrory.UsuarioTestFactory.NOME;
import static com.fiap.pj.core.usuario.util.factrory.UsuarioTestFactory.PERFIL_USUARIO;
import static com.fiap.pj.core.usuario.util.factrory.UsuarioTestFactory.SENHA;
import static com.fiap.pj.core.usuario.util.factrory.UsuarioTestFactory.SOBRE_NOME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UsuarioTest {


    @Test
    @DisplayName("Deve criar com sucesso uma instância de usuario.")
    void deveCriarUsuario() {

        var usuario = UsuarioTestFactory.umUsuario();

        assertEquals(NOME, usuario.getNome());
        assertEquals(SOBRE_NOME, usuario.getSobreNome());
        assertEquals(E_MAIL, usuario.getEmail());
        assertEquals(SENHA, usuario.getSenha());
        assertTrue(usuario.getPerfis().contains(PERFIL_USUARIO));
        assertTrue(usuario.isAtivo());
    }
}
