package com.fiap.pj.core.cliente.app;


import com.fiap.pj.core.cliente.app.gateways.ClienteGateway;
import com.fiap.pj.core.cliente.app.usecase.command.ExistsClienteByCpfAndAtivoCommand;
import com.fiap.pj.core.cliente.util.factory.ClienteTestFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExistsClienteByCpfUseCaseImplTest {

    @Mock
    private ClienteGateway clienteGateway;

    @InjectMocks
    private ExistsClienteByCpfUseCaseImpl existsClienteByCpfUseCaseImpl;

    @Test
    void deveValidarExisteneciaDeCliente() {
        var cpf = ClienteTestFactory.NUMERO_DOCUMENTO;
        when(clienteGateway.existsByDocumentoIdentificacaoNumeroAndAtivo(cpf)).thenReturn(true);
        existsClienteByCpfUseCaseImpl.handle(new ExistsClienteByCpfAndAtivoCommand(cpf));
        verify(clienteGateway).existsByDocumentoIdentificacaoNumeroAndAtivo(cpf);
    }


}
