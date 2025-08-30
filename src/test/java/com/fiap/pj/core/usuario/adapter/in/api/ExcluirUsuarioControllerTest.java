package com.fiap.pj.core.usuario.adapter.in.api;


import com.fiap.pj.core.usuario.app.usecase.ExcluirUsuarioUseCase;
import com.fiap.pj.core.usuario.app.usecase.command.ExcluirUsuarioCommand;
import com.fiap.pj.core.usuario.exception.UsuarioExceptions.UsuarioComRelacionamentoException;
import com.fiap.pj.core.util.TestUtils;
import com.fiap.pj.infra.usuario.controller.UsuarioController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
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
    void deveExcluirUsuario() throws Exception {

        mock.perform(delete(
                TestUtils.buildURL(UsuarioController.PATH, UUID.randomUUID().toString()))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().is2xxSuccessful());

    }

    @Test
    void deveRetornarServicoComRelacionamentoException() {

        Mockito.doThrow(DataIntegrityViolationException.class)
                .when(excluirUsuarioUseCase)
                .handle(Mockito.any(ExcluirUsuarioCommand.class));

        var thrown = catchThrowable(() -> mock.perform(delete(
                TestUtils.buildURL(UsuarioController.PATH, UUID.randomUUID().toString()))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        ));
        assertThat(thrown.getCause()).isInstanceOf(UsuarioComRelacionamentoException.class);
    }

}
