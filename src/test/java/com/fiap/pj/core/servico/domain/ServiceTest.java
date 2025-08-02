package com.fiap.pj.core.servico.domain;

import com.fiap.pj.core.servico.util.factory.ServiceTestFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ServiceTest {


    @Test
    @DisplayName("Deve criar com sucesso uma instância de servico.")
    void deveCriarServico() {
        var service = ServiceTestFactory.umServico();


        assertEquals(ServiceTestFactory.ID, service.getId());
        assertEquals(ServiceTestFactory.DESCRICAO, service.getDescricao());
        assertEquals(ServiceTestFactory.PRECO, service.getPreco());
        assertEquals(ServiceTestFactory.OBSERVACAO, service.getObservacao());
        assertTrue(service.isAtivo());
    }

    @Nested
    class FalhaNaCriacao {

        @Test
        void deveFalharComIdInvalido() {
            assertThrows(NullPointerException.class,
                    () -> new Servico(null,
                            null,
                            null,
                            null, true));
        }

        @Test
        void deveFalharComDescricaoNula() {
            assertThrows(NullPointerException.class,
                    () -> new Servico(ServiceTestFactory.ID,
                            null,
                            null,
                            null, true));
        }


    }
}
