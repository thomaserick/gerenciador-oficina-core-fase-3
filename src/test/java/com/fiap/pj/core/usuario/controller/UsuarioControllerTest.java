package com.fiap.pj.core.usuario.controller;


import com.fiap.pj.core.usuario.adapter.in.api.UsuarioController;
import com.fiap.pj.core.usuario.usecase.CriarUsuarioUseCase;
import com.fiap.pj.core.usuario.usecase.command.UsuarioCommand;
import com.fiap.pj.core.usuario.util.factrory.UsuarioTestFactory;
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

import static com.fiap.pj.core.usuario.util.factrory.UsuarioTestFactory.umUsuarioCommand;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UsuarioControllerTest {

    @Mock
    private CriarUsuarioUseCase criarUsuarioUseCase;

    @InjectMocks
    private UsuarioController usuarioController;

    private MockMvc mock;

    @BeforeEach
    void setup() {
        mock = MockMvcBuilders.standaloneSetup(usuarioController).build();
    }

    @Test
    void deveCriarUsuario() throws Exception {

        Mockito.when(criarUsuarioUseCase.handle(Mockito.any(UsuarioCommand.class))).thenReturn(UsuarioTestFactory.umUsuario());

        mock.perform(post(
                TestUtils.buildURL(UsuarioController.PATH))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtils.objectToJson(umUsuarioCommand()))).andExpect(status().is2xxSuccessful());

    }

}
