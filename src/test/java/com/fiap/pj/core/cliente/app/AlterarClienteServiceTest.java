package com.fiap.pj.core.cliente.app;


import com.fiap.pj.core.cliente.adapter.out.db.ClienteRepositoryJpa;
import com.fiap.pj.core.cliente.domain.Cliente;
import com.fiap.pj.core.cliente.exception.ClienteExceptions.ClienteNaoEncontradoException;
import com.fiap.pj.core.cliente.util.factory.ClienteTestFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AlterarClienteServiceTest {

    @Captor
    ArgumentCaptor<Cliente> clienteCaptor;
    @Mock
    private ClienteRepositoryJpa clienteRepositoryJpa;
    @InjectMocks
    private AlterarClienteService alterarClienteService;

    @Test
    void shouldAlterarCustomer() {
        var cliente = ClienteTestFactory.oneCustomer();
        when(clienteRepositoryJpa.findByIdOrThrowNotFound(cliente.getId())).thenReturn(cliente);

        alterarClienteService.handle(ClienteTestFactory.onUpdateCustomerCommand(cliente.getId()));

        verify(clienteRepositoryJpa).save(clienteCaptor.capture());
        Cliente clienteUpdated = clienteCaptor.getValue();

        assertNotNull(clienteUpdated);
        assertEquals(ClienteTestFactory.ID, clienteUpdated.getId());
        assertEquals(ClienteTestFactory.ALTER_NAME, clienteUpdated.getNome());
        assertEquals(ClienteTestFactory.ALTER_PHONE, clienteUpdated.getTelefone());
        assertEquals(ClienteTestFactory.ALTER_E_MAIL, clienteUpdated.getEmail());
        assertEquals(ClienteTestFactory.ALTER_ADDRESS, clienteUpdated.getEndereco());


        verify(clienteRepositoryJpa).save(cliente);

    }

    @Test
    void shouldReturnCustomerNotFoundException() {
        var cliente = ClienteTestFactory.oneCustomer();

        Mockito.doThrow(new ClienteNaoEncontradoException())
                .when(clienteRepositoryJpa)
                .findByIdOrThrowNotFound(cliente.getId());

        var thrown = catchThrowable(() -> alterarClienteService.handle(ClienteTestFactory.onUpdateCustomerCommand(cliente.getId())));
        assertThat(thrown).isInstanceOf(ClienteNaoEncontradoException.class);
    }
}
