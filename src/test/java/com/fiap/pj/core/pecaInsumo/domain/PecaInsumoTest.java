package com.fiap.pj.core.pecaInsumo.domain;

import com.fiap.pj.core.pecaInsumo.util.factory.PecaInsumoTestFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.fiap.pj.core.pecaInsumo.util.factory.PecaInsumoTestFactory.*;
import static org.junit.jupiter.api.Assertions.*;

class PecaInsumoTest {

    @Test
    @DisplayName("Deve criar com sucesso uma instância de peça/insumo.")
    void deveCriarPecaInsumo() {
        var pecaInsumo = PecaInsumoTestFactory.onePecaInsumo();

        assertEquals(NOME, pecaInsumo.getNome());
        assertEquals(DESCRICAO, pecaInsumo.getDescricao());
        assertEquals(VALOR_UNITARIO, pecaInsumo.getValorUnitario());
        assertEquals(QUANTIDADE_ESTOQUE, pecaInsumo.getQuantidadeEstoque());
        assertEquals(QUANTIDADE_MINIMO_ESTOQUE, pecaInsumo.getQuantidadeMinimoEstoque());
    }

    @Test
    @DisplayName("Deve adicionar estoque com sucesso.")
    void deveAdicionarEstoque() {
        var pecaInsumo = PecaInsumoTestFactory.onePecaInsumo();
        var quantidadeInicial = pecaInsumo.getQuantidadeEstoque();

        pecaInsumo.adicionarEstoque(5);

        assertEquals(quantidadeInicial + 5, pecaInsumo.getQuantidadeEstoque());
    }

    @Test
    @DisplayName("Deve remover estoque com sucesso.")
    void deveRemoverEstoque() {
        var pecaInsumo = PecaInsumoTestFactory.onePecaInsumo();
        var quantidadeInicial = pecaInsumo.getQuantidadeEstoque();

        pecaInsumo.removerEstoque(3);

        assertEquals(quantidadeInicial - 3, pecaInsumo.getQuantidadeEstoque());
    }

    @Test
    @DisplayName("Deve atualizar dados com sucesso.")
    void deveAtualizarDados() {
        var pecaInsumo = PecaInsumoTestFactory.onePecaInsumo();
        var novoNome = "Óleo Sintético";
        var novaDescricao = "Óleo sintético de alta performance";
        var novoValor = new BigDecimal("55.00");
        var novoMinimoEstoque = 8;

        pecaInsumo.atualizarDados(novoNome, novaDescricao, novoValor, novoMinimoEstoque);

        assertEquals(novoNome, pecaInsumo.getNome());
        assertEquals(novaDescricao, pecaInsumo.getDescricao());
        assertEquals(novoValor, pecaInsumo.getValorUnitario());
        assertEquals(novoMinimoEstoque, pecaInsumo.getQuantidadeMinimoEstoque());
    }

    @Test
    @DisplayName("Deve identificar estoque baixo quando quantidade é menor ou igual ao mínimo.")
    void deveIdentificarEstoqueBaixo() {
        var pecaInsumo = PecaInsumoTestFactory.onePecaInsumo();
        
        // Estoque inicial (10) é maior que o mínimo (5), então não está baixo
        assertFalse(pecaInsumo.estoqueBaixo());
        
        // Estoque igual ao mínimo (5)
        pecaInsumo.removerEstoque(5);
        assertTrue(pecaInsumo.estoqueBaixo());
        
        // Estoque abaixo do mínimo (4)
        pecaInsumo.removerEstoque(1);
        assertTrue(pecaInsumo.estoqueBaixo());
        
        // Estoque acima do mínimo (9)
        pecaInsumo.adicionarEstoque(5);
        assertFalse(pecaInsumo.estoqueBaixo());
    }

    @Nested
    class FalhaNaCriacao {

        @Test
        void deveFalharComNomeInvalido() {
            assertThrows(NullPointerException.class,
                    () -> new PecaInsumo(PecaInsumoTestFactory.ID,
                            null,
                            DESCRICAO,
                            VALOR_UNITARIO, QUANTIDADE_ESTOQUE, QUANTIDADE_MINIMO_ESTOQUE));
        }

        @Test
        void deveFalharComDescricaoInvalida() {
            assertThrows(NullPointerException.class,
                    () -> new PecaInsumo(PecaInsumoTestFactory.ID,
                            NOME,
                            null,
                            VALOR_UNITARIO, QUANTIDADE_ESTOQUE, QUANTIDADE_MINIMO_ESTOQUE));
        }

        @Test
        void deveFalharComValorUnitarioInvalido() {
            assertThrows(NullPointerException.class,
                    () -> new PecaInsumo(PecaInsumoTestFactory.ID,
                            NOME,
                            DESCRICAO,
                            null, QUANTIDADE_ESTOQUE, QUANTIDADE_MINIMO_ESTOQUE));
        }

        @Test
        void deveFalharComQuantidadeEstoqueInvalida() {
            assertThrows(NullPointerException.class,
                    () -> new PecaInsumo(PecaInsumoTestFactory.ID,
                            NOME,
                            DESCRICAO,
                            VALOR_UNITARIO, null, QUANTIDADE_MINIMO_ESTOQUE));
        }

        @Test
        void deveFalharComQuantidadeMinimoEstoqueInvalida() {
            assertThrows(NullPointerException.class,
                    () -> new PecaInsumo(PecaInsumoTestFactory.ID,
                            NOME,
                            DESCRICAO,
                            VALOR_UNITARIO, QUANTIDADE_ESTOQUE, null));
        }
    }

    @Nested
    class FalhaNaGestaoDeEstoque {

        @Test
        void deveFalharAoAdicionarQuantidadeNegativa() {
            var pecaInsumo = PecaInsumoTestFactory.onePecaInsumo();

            assertThrows(IllegalArgumentException.class,
                    () -> pecaInsumo.adicionarEstoque(-1));
        }

        @Test
        void deveFalharAoAdicionarQuantidadeZero() {
            var pecaInsumo = PecaInsumoTestFactory.onePecaInsumo();

            assertThrows(IllegalArgumentException.class,
                    () -> pecaInsumo.adicionarEstoque(0));
        }

        @Test
        void deveFalharAoRemoverQuantidadeNegativa() {
            var pecaInsumo = PecaInsumoTestFactory.onePecaInsumo();

            assertThrows(IllegalArgumentException.class,
                    () -> pecaInsumo.removerEstoque(-1));
        }

        @Test
        void deveFalharAoRemoverQuantidadeZero() {
            var pecaInsumo = PecaInsumoTestFactory.onePecaInsumo();

            assertThrows(IllegalArgumentException.class,
                    () -> pecaInsumo.removerEstoque(0));
        }

        @Test
        void deveFalharAoRemoverQuantidadeMaiorQueEstoque() {
            var pecaInsumo = PecaInsumoTestFactory.onePecaInsumo();

            assertThrows(IllegalArgumentException.class,
                    () -> pecaInsumo.removerEstoque(QUANTIDADE_ESTOQUE + 1));
        }
    }
} 