package com.fiap.pj.core.usuario.app;


import com.fiap.pj.core.usuario.adapter.out.UserRepositoryJpa;
import com.fiap.pj.core.usuario.domain.User;
import com.fiap.pj.core.usuario.util.factrory.UserTestFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.fiap.pj.core.usuario.util.factrory.UserTestFactory.E_MAIL;
import static com.fiap.pj.core.usuario.util.factrory.UserTestFactory.ID;
import static com.fiap.pj.core.usuario.util.factrory.UserTestFactory.NOME;
import static com.fiap.pj.core.usuario.util.factrory.UserTestFactory.PERFIL_USUARIO;
import static com.fiap.pj.core.usuario.util.factrory.UserTestFactory.SENHA;
import static com.fiap.pj.core.usuario.util.factrory.UserTestFactory.SOBRE_NOME;
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
    void deveCriarUsuario() {
        when(userRepositoryJpa.save(any(User.class))).thenReturn(UserTestFactory.umUsuario());

        var usuario = createUserService.handle(UserTestFactory.umCriarUsuarioCommand());

        assertNotNull(usuario);
        assertEquals(ID, usuario.getId());
        assertEquals(NOME, usuario.getName());
        assertEquals(SOBRE_NOME, usuario.getLastName());
        assertEquals(E_MAIL, usuario.getEmail());
        assertEquals(SENHA, usuario.getPassword());
        assertTrue(usuario.getRoles().contains(PERFIL_USUARIO));
    }
}
