package com.fiap.pj.core.cliente.app;


import com.fiap.pj.core.cliente.app.usecase.ExcluirClienteUseCaseImpl;
import com.fiap.pj.core.cliente.app.usecase.command.ExcluirClienteCommand;
import com.fiap.pj.core.cliente.domain.Cliente;
import com.fiap.pj.core.cliente.util.factory.ClienteTestFactory;
import com.fiap.pj.infra.cliente.persistence.ClienteRepositoryJpa;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExcluirClienteUseCaseImplTest {

    @Mock
    private ClienteRepositoryJpa clienteRepositoryJpa;

    @InjectMocks
    private ExcluirClienteUseCaseImpl excluirClienteUseCaseImpl;


    @Test
    void deveExcluirCliente() {
        var id = UUID.randomUUID();
        when(clienteRepositoryJpa.findByIdOrThrowNotFound(id)).thenReturn(ClienteTestFactory.umCliente());
        excluirClienteUseCaseImpl.handle(new ExcluirClienteCommand(id));
        verify(clienteRepositoryJpa).delete(Mockito.any(Cliente.class));
    }
}
