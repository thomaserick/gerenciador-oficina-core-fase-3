package com.fiap.pj.core.user.app;


import com.fiap.pj.core.user.adapter.out.db.UserRepositoryJpa;
import com.fiap.pj.core.user.domain.User;
import com.fiap.pj.core.user.util.factrory.UserTestFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.fiap.pj.core.user.util.factrory.UserTestFactory.E_MAIL;
import static com.fiap.pj.core.user.util.factrory.UserTestFactory.ID;
import static com.fiap.pj.core.user.util.factrory.UserTestFactory.LAST_NAME;
import static com.fiap.pj.core.user.util.factrory.UserTestFactory.NAME;
import static com.fiap.pj.core.user.util.factrory.UserTestFactory.PASSWORD;
import static com.fiap.pj.core.user.util.factrory.UserTestFactory.USER_ROLE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateUserServiceTest {

    @Mock
    private UserRepositoryJpa userRepositoryJpa;

    @InjectMocks
    private CreateUserService createUserService;

    @Test
    void shouldCreateUser() {
        when(userRepositoryJpa.save(any(User.class))).thenReturn(UserTestFactory.oneUser());

        var usuario = createUserService.handle(UserTestFactory.oneCreateUserCommand());

        assertNotNull(usuario);
        assertEquals(ID, usuario.getId());
        assertEquals(NAME, usuario.getName());
        assertEquals(LAST_NAME, usuario.getLastName());
        assertEquals(E_MAIL, usuario.getEmail());
        assertEquals(PASSWORD, usuario.getPassword());
        assertTrue(usuario.getRoles().contains(USER_ROLE));
    }
}
