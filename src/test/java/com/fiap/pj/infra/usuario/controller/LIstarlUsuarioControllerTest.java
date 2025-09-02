package com.fiap.pj.infra.usuario.controller;


import com.fiap.pj.core.usuario.app.usecase.ListarUsuarioUseCase;
import com.fiap.pj.core.usuario.util.factrory.UsuarioTestFactory;
import com.fiap.pj.core.util.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class LIstarlUsuarioControllerTest {

    @InjectMocks
    private UsuarioController userController;

    @Mock
    private ListarUsuarioUseCase listarUsuarioUseCase;

    private MockMvc mock;

    @BeforeEach
    void setup() {
        mock = MockMvcBuilders.standaloneSetup(userController).setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
    }

    @Test
    void deveListarUsuarios() throws Exception {

        mock.perform(get(
                TestUtils.buildURL(UsuarioController.PATH))
                .param("nome", UsuarioTestFactory.NAME)
                .param("ativo", "true")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isOk());

    }

}
