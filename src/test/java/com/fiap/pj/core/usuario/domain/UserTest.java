package com.fiap.pj.core.usuario.domain;

import com.fiap.pj.core.usuario.util.factrory.UserTestFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.fiap.pj.core.usuario.util.factrory.UserTestFactory.E_MAIL;
import static com.fiap.pj.core.usuario.util.factrory.UserTestFactory.NOME;
import static com.fiap.pj.core.usuario.util.factrory.UserTestFactory.PERFIL_USUARIO;
import static com.fiap.pj.core.usuario.util.factrory.UserTestFactory.SENHA;
import static com.fiap.pj.core.usuario.util.factrory.UserTestFactory.SOBRE_NOME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserTest {


    @Test
    @DisplayName("Deve criar com sucesso uma instância de usuario.")
    void deveCriarUsuario() {

        var usuario = UserTestFactory.umUsuario();

        assertEquals(NOME, usuario.getName());
        assertEquals(SOBRE_NOME, usuario.getLastName());
        assertEquals(E_MAIL, usuario.getEmail());
        assertEquals(SENHA, usuario.getPassword());
        assertTrue(usuario.getRoles().contains(PERFIL_USUARIO));
        assertTrue(usuario.isActive());
    }
}
