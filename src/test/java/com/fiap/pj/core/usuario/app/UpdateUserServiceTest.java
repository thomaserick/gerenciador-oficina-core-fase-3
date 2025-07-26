package com.fiap.pj.core.usuario.app;


import com.fiap.pj.core.usuario.adapter.out.UserRepositoryJpa;
import com.fiap.pj.core.usuario.domain.User;
import com.fiap.pj.core.usuario.util.factrory.UserTestFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.fiap.pj.core.usuario.util.factrory.UserTestFactory.ALTER_LAST_NAME;
import static com.fiap.pj.core.usuario.util.factrory.UserTestFactory.ALTER_NAME;
import static com.fiap.pj.core.usuario.util.factrory.UserTestFactory.ALTER_PASSWORD;
import static com.fiap.pj.core.usuario.util.factrory.UserTestFactory.ALTER_USER_ROLE;
import static com.fiap.pj.core.usuario.util.factrory.UserTestFactory.ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateUserServiceTest {

    @Captor
    ArgumentCaptor<User> userCaptor;
    @Mock
    private UserRepositoryJpa userRepositoryJpa;
    @InjectMocks
    private UpdateUserService updateUserService;

    @Test
    void shouldCreateUser() {
        var user = UserTestFactory.oneUser();
        when(userRepositoryJpa.findByIdOrThrowNotFound(user.getId())).thenReturn(user);

        updateUserService.handle(UserTestFactory.umUpdateUserCommand(user.getId()));

        verify(userRepositoryJpa).save(userCaptor.capture());
        User usuario = userCaptor.getValue();

        assertNotNull(usuario);
        assertEquals(ID, usuario.getId());
        assertEquals(ALTER_NAME, usuario.getName());
        assertEquals(ALTER_LAST_NAME, usuario.getLastName());
        assertEquals(ALTER_PASSWORD, usuario.getPassword());
        assertTrue(usuario.getRoles().contains(ALTER_USER_ROLE));

        verify(userRepositoryJpa).save(user);
    }
}
