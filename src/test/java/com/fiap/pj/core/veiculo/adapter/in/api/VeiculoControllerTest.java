package com.fiap.pj.core.veiculo.adapter.in.api;


import com.fiap.pj.core.cliente.adapter.in.api.ClienteVeiculoController;
import com.fiap.pj.core.cliente.util.factory.ClienteTestFactory;
import com.fiap.pj.core.util.TestUtils;
import com.fiap.pj.core.veiculo.usecase.AdicionarVeiculoClienteUseCase;
import com.fiap.pj.core.veiculo.util.factory.VeiculoTestFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.fiap.pj.core.veiculo.util.factory.VeiculoTestFactory.umAdicionarVeiculoClienteCommand;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class VeiculoControllerTest {

    @Mock
    private AdicionarVeiculoClienteUseCase adicionarVeiculoClienteUseCase;

    @InjectMocks
    private ClienteVeiculoController clienteVeiculoController;

    private MockMvc mock;

    @BeforeEach
    void setup() {
        mock = MockMvcBuilders.standaloneSetup(clienteVeiculoController).build();
    }

    @Test
    void deveAdicionarVeiculoAoCliente() throws Exception {
        mock.perform(post(
                TestUtils.buildURL(ClienteVeiculoController.PATH.replace("{id}", VeiculoTestFactory.ID.toString())))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtils.objectToJson(umAdicionarVeiculoClienteCommand(ClienteTestFactory.ID)))).andExpect(status().is2xxSuccessful());
    }

}
