package com.fiap.pj.core.cliente.adapter.in.api;


import com.fiap.pj.core.cliente.usecase.CriarClienteUserCase;
import com.fiap.pj.core.cliente.usecase.command.CriarClienteCommand;
import com.fiap.pj.core.cliente.util.factory.ClienteTestFactory;
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

import static com.fiap.pj.core.cliente.util.factory.ClienteTestFactory.onCrateCustomerCNPJCommand;
import static com.fiap.pj.core.cliente.util.factory.ClienteTestFactory.onCrateCustomerCommand;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CriarControllerTest {

    @Mock
    private CriarClienteUserCase criarClienteUserCase;

    @InjectMocks
    private ClienteController clienteController;

    private MockMvc mock;

    @BeforeEach
    void setup() {
        mock = MockMvcBuilders.standaloneSetup(clienteController).build();
    }

    @Test
    void shouldCreateCustomer() throws Exception {

        Mockito.when(criarClienteUserCase.handle(Mockito.any(CriarClienteCommand.class))).thenReturn(ClienteTestFactory.oneCustomer());

        mock.perform(post(
                TestUtils.buildURL(ClienteController.PATH))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtils.objectToJson(onCrateCustomerCommand()))).andExpect(status().is2xxSuccessful());
    }

    @Test
    void shouldCreateCustomerWithCNPJ() throws Exception {
        Mockito.when(criarClienteUserCase.handle(Mockito.any(CriarClienteCommand.class))).thenReturn(ClienteTestFactory.oneCustomerCNPJ());
        mock.perform(post(
                TestUtils.buildURL(ClienteController.PATH))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtils.objectToJson(onCrateCustomerCNPJCommand()))).andExpect(status().is2xxSuccessful());

    }

}
