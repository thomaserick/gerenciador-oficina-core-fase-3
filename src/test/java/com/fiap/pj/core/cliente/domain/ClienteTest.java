package com.fiap.pj.core.cliente.domain;

import com.fiap.pj.core.cliente.util.factory.ClienteTestFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.fiap.pj.core.cliente.util.factory.ClienteTestFactory.ENDERECO;
import static com.fiap.pj.core.cliente.util.factory.ClienteTestFactory.NUMERO_DOCUMENTO;
import static com.fiap.pj.core.cliente.util.factory.ClienteTestFactory.TELEFONE;
import static com.fiap.pj.core.usuario.util.factrory.UserTestFactory.E_MAIL;
import static com.fiap.pj.core.usuario.util.factrory.UserTestFactory.NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClienteTest {


    @Test
    @DisplayName("Deve criar com sucesso uma instância de Cliente.")
    void shouldCrateCustomer() {

        var cliente = ClienteTestFactory.oneCustomer();

        assertEquals(NAME, cliente.getNome());
        assertEquals(E_MAIL, cliente.getEmail());
        assertEquals(TELEFONE, cliente.getTelefone());
        assertEquals(ENDERECO, cliente.getEndereco());
        assertEquals(NUMERO_DOCUMENTO, cliente.getDocumentoIdentificacao().getNumero());
        assertTrue(cliente.isAtivo());
    }

    @Nested
    class CreationFailure {

        @Test
        void ShouldNotCreateCustomerWithoutName() {
            assertThrows(NullPointerException.class,
                    () -> new Cliente(ClienteTestFactory.ID,
                            null,
                            null,
                            null, true, null, null));
        }

        @Test
        void ShouldNotCreateCustomerWithoutIdentificationDocument() {
            assertThrows(NullPointerException.class,
                    () -> new Cliente(ClienteTestFactory.ID,
                            ClienteTestFactory.NOME,
                            null,
                            null, true, ENDERECO, null));
        }
    }
}
