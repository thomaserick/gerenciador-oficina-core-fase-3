package com.fiap.pj.core.usuario.adapter.in.api;


import com.fiap.pj.core.usuario.usecase.ExcluirUsuarioUseCase;
import com.fiap.pj.core.util.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static com.fiap.pj.core.usuario.util.factrory.UserTestFactory.oneCreateUserCommand;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ExcluirUsuarioControllerTest {

    @Mock
    private ExcluirUsuarioUseCase excluirUsuarioUseCase;

    @InjectMocks
    private UsuarioController userController;

    private MockMvc mock;

    @BeforeEach
    void setup() {
        mock = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void deveCriarUsuario() throws Exception {

        mock.perform(delete(
                TestUtils.buildURL(UsuarioController.PATH, UUID.randomUUID().toString()))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtils.objectToJson(oneCreateUserCommand()))).andExpect(status().is2xxSuccessful());

    }

}
