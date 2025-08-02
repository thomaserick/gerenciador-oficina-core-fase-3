package com.fiap.pj.core.cliente.app;


import com.fiap.pj.core.cliente.adapter.out.db.ClienteRepositoryJpa;
import com.fiap.pj.core.cliente.domain.Cliente;
import com.fiap.pj.core.cliente.exception.ClienteExceptions.DocumentoIdentificacaoDuplicadoException;
import com.fiap.pj.core.cliente.util.factory.ClienteTestFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.fiap.pj.core.cliente.util.factory.ClienteTestFactory.ENDERECO;
import static com.fiap.pj.core.cliente.util.factory.ClienteTestFactory.NUMERO_DOCUMENTO;
import static com.fiap.pj.core.cliente.util.factory.ClienteTestFactory.TELEFONE;
import static com.fiap.pj.core.usuario.util.factrory.UserTestFactory.E_MAIL;
import static com.fiap.pj.core.usuario.util.factrory.UserTestFactory.NAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CriarClienteServiceTest {

    @Mock
    private ClienteRepositoryJpa clienteRepositoryJpa;

    @InjectMocks
    private CriarClienteService criarClienteService;

    @Test
    void deveCriarCliente() {
        when(clienteRepositoryJpa.save(any(Cliente.class))).thenReturn(ClienteTestFactory.umCliente());

        var cliente = criarClienteService.handle(ClienteTestFactory.umCriarClienteCommand());

        assertEquals(NAME, cliente.getNome());
        assertEquals(E_MAIL, cliente.getEmail());
        assertEquals(TELEFONE, cliente.getTelefone());
        assertEquals(ENDERECO, cliente.getEndereco());
        assertEquals(NUMERO_DOCUMENTO, cliente.getDocumentoIdentificacao().getNumero());
        assertTrue(cliente.isAtivo());
    }

    @Test
    void deveRetornarDocumentoIdentificacaoDuplicadoException() {

        Mockito.when(clienteRepositoryJpa.existsByIDocumentoIdentificacaoNumero(Mockito.anyString())).thenReturn(true);

        var thrown = catchThrowable(() -> criarClienteService.handle(ClienteTestFactory.umCriarClienteCommand()));
        assertThat(thrown).isInstanceOf(DocumentoIdentificacaoDuplicadoException.class);

    }
}
