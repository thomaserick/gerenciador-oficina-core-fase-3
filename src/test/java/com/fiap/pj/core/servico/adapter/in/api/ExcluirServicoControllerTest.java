package com.fiap.pj.core.servico.adapter.in.api;


import com.fiap.pj.core.servico.exception.ServicoExceptions.ServicoComRelacionamentoException;
import com.fiap.pj.core.servico.usecase.ExcluirServicoUserCase;
import com.fiap.pj.core.servico.usecase.command.ExcluirServicoCommand;
import com.fiap.pj.core.util.TestUtils;
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
class ExcluirServicoControllerTest {

    @Mock
    private ExcluirServicoUserCase excluirServicoUserCase;

    @InjectMocks
    private ServicoController servicoController;

    private MockMvc mock;

    @BeforeEach
    void setup() {
        mock = MockMvcBuilders.standaloneSetup(servicoController).build();
    }

    @Test
    void deveDeletarServico() throws Exception {

        mock.perform(delete(
                TestUtils.buildURL(ServicoController.PATH, UUID.randomUUID().toString()))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().is2xxSuccessful());
    }

    @Test
    void deveRetornarServicoComRelacionamentoException() {

        Mockito.doThrow(DataIntegrityViolationException.class)
                .when(excluirServicoUserCase)
                .handle(Mockito.any(ExcluirServicoCommand.class));

        var thrown = catchThrowable(() -> mock.perform(delete(
                TestUtils.buildURL(ServicoController.PATH, UUID.randomUUID().toString()))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        ));
        assertThat(thrown.getCause()).isInstanceOf(ServicoComRelacionamentoException.class);
    }

}
