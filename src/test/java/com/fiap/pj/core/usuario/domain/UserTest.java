package com.fiap.pj.core.usuario.domain;

import com.fiap.pj.core.usuario.util.factrory.UserTestFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.fiap.pj.core.usuario.util.factrory.UserTestFactory.E_MAIL;
import static com.fiap.pj.core.usuario.util.factrory.UserTestFactory.LAST_NAME;
import static com.fiap.pj.core.usuario.util.factrory.UserTestFactory.NAME;
import static com.fiap.pj.core.usuario.util.factrory.UserTestFactory.PASSWORD;
import static com.fiap.pj.core.usuario.util.factrory.UserTestFactory.USER_ROLE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserTest {


    @Test
    @DisplayName("Deve criar com sucesso uma instância de usuario.")
    void shouldCrateUser() {

        var usuario = UserTestFactory.oneUser();

        assertEquals(NAME, usuario.getName());
        assertEquals(LAST_NAME, usuario.getLastName());
        assertEquals(E_MAIL, usuario.getEmail());
        assertEquals(PASSWORD, usuario.getPassword());
        assertTrue(usuario.getRoles().contains(USER_ROLE));
        assertTrue(usuario.isActive());
    }
}
