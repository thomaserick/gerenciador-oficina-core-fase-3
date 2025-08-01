package com.fiap.pj.core.user.app;


import com.fiap.pj.core.user.adapter.out.db.UserRepositoryJpa;
import com.fiap.pj.core.user.domain.User;
import com.fiap.pj.core.user.exception.UserExceptions.UserNotFoundException;
import com.fiap.pj.core.user.util.factrory.UserTestFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.fiap.pj.core.user.util.factrory.UserTestFactory.ALTER_LAST_NAME;
import static com.fiap.pj.core.user.util.factrory.UserTestFactory.ALTER_NAME;
import static com.fiap.pj.core.user.util.factrory.UserTestFactory.ALTER_PASSWORD;
import static com.fiap.pj.core.user.util.factrory.UserTestFactory.ALTER_USER_ROLE;
import static com.fiap.pj.core.user.util.factrory.UserTestFactory.ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
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
    void shouldUpdateUser() {
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

    @Test
    void shouldReturnUserNotFoundException() {
        var user = UserTestFactory.oneUser();

        Mockito.doThrow(new UserNotFoundException())
                .when(userRepositoryJpa)
                .findByIdOrThrowNotFound(user.getId());

        var thrown = catchThrowable(() -> updateUserService.handle(UserTestFactory.umUpdateUserCommand(user.getId())));
        assertThat(thrown).isInstanceOf(UserNotFoundException.class);

    }
}
