package com.fiap.pj.core.servico.adapter.in.api;


import com.fiap.pj.core.servico.usecase.CriarServicoUseCase;
import com.fiap.pj.core.servico.usecase.command.CriarServicoCommand;
import com.fiap.pj.core.servico.util.factory.ServiceTestFactory;
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

import static com.fiap.pj.core.servico.util.factory.ServiceTestFactory.oneCreateServiceCommand;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CreateServiceControllerTest {

    @Mock
    private CriarServicoUseCase criarServicoUseCase;

    @InjectMocks
    private ServicoController serviceController;

    private MockMvc mock;

    @BeforeEach
    void setup() {
        mock = MockMvcBuilders.standaloneSetup(serviceController).build();
    }

    @Test
    void deveCriarServico() throws Exception {

        Mockito.when(criarServicoUseCase.handle(Mockito.any(CriarServicoCommand.class))).thenReturn(ServiceTestFactory.oneService());

        mock.perform(post(
                TestUtils.buildURL(ServicoController.PATH))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtils.objectToJson(oneCreateServiceCommand()))).andExpect(status().is2xxSuccessful());

    }

}
