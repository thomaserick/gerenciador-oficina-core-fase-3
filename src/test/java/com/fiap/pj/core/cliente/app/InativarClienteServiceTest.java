package com.fiap.pj.core.cliente.app;


import com.fiap.pj.core.cliente.adapter.out.db.ClienteRepositoryJpa;
import com.fiap.pj.core.cliente.domain.Cliente;
import com.fiap.pj.core.cliente.exception.ClienteExceptions.ClienteNaoEncontradoException;
import com.fiap.pj.core.cliente.usecase.command.InativarClienteCommand;
import com.fiap.pj.core.cliente.util.factory.ClienteTestFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InativarClienteServiceTest {

    @Mock
    private ClienteRepositoryJpa clienteRepositoryJpa;

    @InjectMocks
    private InativarClienteService inativarClienteService;

    @Test
    void deveAtivarCliente() {
        var id = UUID.randomUUID();
        when(clienteRepositoryJpa.findByIdOrThrowNotFound(id)).thenReturn(ClienteTestFactory.umCliente());
        inativarClienteService.handle(new InativarClienteCommand(id));
        verify(clienteRepositoryJpa).save(Mockito.any(Cliente.class));
    }

    @Test
    void deveRetornarClienteNaoEncontradoException() {
        var id = UUID.randomUUID();

        Mockito.doThrow(new ClienteNaoEncontradoException())
                .when(clienteRepositoryJpa)
                .findByIdOrThrowNotFound(id);

        var thrown = catchThrowable(() -> inativarClienteService.handle(new InativarClienteCommand(id)));
        assertThat(thrown).isInstanceOf(ClienteNaoEncontradoException.class);

    }


}
