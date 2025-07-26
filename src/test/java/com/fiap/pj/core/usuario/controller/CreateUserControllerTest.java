package com.fiap.pj.core.usuario.controller;


import com.fiap.pj.core.usuario.adapter.in.api.UserController;
import com.fiap.pj.core.usuario.usecase.CreateUserUseCase;
import com.fiap.pj.core.usuario.usecase.command.CreateUserCommand;
import com.fiap.pj.core.usuario.util.factrory.UserTestFactory;
import com.fiap.pj.core.util.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.fiap.pj.core.usuario.util.factrory.UserTestFactory.umCriarUsuarioCommand;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CreateUserControllerTest {

    @Mock
    private CreateUserUseCase createUserUseCase;

    @InjectMocks
    private UserController userController;

    private MockMvc mock;

    @BeforeEach
    void setup() {
        mock = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void deveCreateUser() throws Exception {

        Mockito.when(createUserUseCase.handle(Mockito.any(CreateUserCommand.class))).thenReturn(UserTestFactory.umUsuario());

        mock.perform(post(
                TestUtils.buildURL(UserController.PATH))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtils.objectToJson(umCriarUsuarioCommand()))).andExpect(status().is2xxSuccessful());

    }

}
