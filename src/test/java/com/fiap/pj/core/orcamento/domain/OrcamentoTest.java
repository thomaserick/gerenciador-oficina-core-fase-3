package com.fiap.pj.core.orcamento.domain;

import com.fiap.pj.core.orcamento.util.factory.OrcamentoTestFactory;
import com.fiap.pj.core.servico.util.factory.ServicoTestFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrcamentoTest {


    @Test
    @DisplayName("Deve criar com sucesso uma instância de orcamento.")
    void deveCriarServico() {
        var orcamento = OrcamentoTestFactory.umOrcamento();
        orcamento.adicionarServico(OrcamentoTestFactory.umOrcamentoItemServico(orcamento.getId()));

        assertEquals(OrcamentoTestFactory.ID, orcamento.getId());
        assertEquals(OrcamentoTestFactory.DESCRICAO, orcamento.getDescricao());
        assertEquals(OrcamentoTestFactory.CLIENTE_ID, orcamento.getClienteId());
        assertEquals(OrcamentoTestFactory.VEICULO_ID, orcamento.getVeiculoId());
        assertEquals(OrcamentoTestFactory.HODOMENTO, orcamento.getHodometro());
        assertEquals(OrcamentoTestFactory.ORCAMENTO_STATUS, orcamento.getStatus());
        assertEquals(ServicoTestFactory.PRECO, orcamento.getValorTotal());
        assertFalse(orcamento.getServicos().isEmpty());
    }

    @Nested
    class FalhaNaCriacao {

        @Test
        void deveFalharComIdInvalido() {
            assertThrows(NullPointerException.class,
                    () -> new Orcamento(null,
                            null,
                            null,
                            null, 0, null, null));
        }

        @Test
        void deveFalharComClienteIdInvalido() {
            assertThrows(NullPointerException.class,
                    () -> new Orcamento(OrcamentoTestFactory.ID,
                            null,
                            null,
                            null, 0, null, null));
        }

        @Test
        void deveFalharComVeiculoIdInvalido() {
            assertThrows(NullPointerException.class,
                    () -> new Orcamento(OrcamentoTestFactory.ID,
                            null,
                            OrcamentoTestFactory.CLIENTE_ID,
                            null, 0, null, null));
        }

        @Test
        void deveFalharComStatusIdInvalido() {
            assertThrows(NullPointerException.class,
                    () -> new Orcamento(OrcamentoTestFactory.ID,
                            null,
                            OrcamentoTestFactory.CLIENTE_ID,
                            OrcamentoTestFactory.VEICULO_ID, 0, null, null));
        }


    }
}

