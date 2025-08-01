package com.fiap.pj.core.user.domain;

import com.fiap.pj.core.user.util.factrory.UserTestFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.fiap.pj.core.user.util.factrory.UserTestFactory.E_MAIL;
import static com.fiap.pj.core.user.util.factrory.UserTestFactory.LAST_NAME;
import static com.fiap.pj.core.user.util.factrory.UserTestFactory.NAME;
import static com.fiap.pj.core.user.util.factrory.UserTestFactory.PASSWORD;
import static com.fiap.pj.core.user.util.factrory.UserTestFactory.USER_ROLE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @Nested
    class CreationFailure {

        @Test
        void ShouldNotCreateUserWithoutName() {
            assertThrows(NullPointerException.class,
                    () -> new User(UserTestFactory.ID,
                            null,
                            null,
                            null, null, true));
        }

        @Test
        void ShouldNotCreateUserWithoutEmail() {
            assertThrows(NullPointerException.class,
                    () -> new User(UserTestFactory.ID,
                            NAME,
                            LAST_NAME,
                            null, null, true));
        }

        @Test
        void ShouldNotCreateUserWithoutPassword() {
            assertThrows(NullPointerException.class,
                    () -> new User(UserTestFactory.ID,
                            NAME,
                            LAST_NAME,
                            E_MAIL, null, true));
        }

    }

}
