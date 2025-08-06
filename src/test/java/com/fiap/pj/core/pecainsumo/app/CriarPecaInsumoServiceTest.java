package com.fiap.pj.core.pecainsumo.app;

import com.fiap.pj.core.pecainsumo.adapter.out.db.PecaInsumoRepositoryJpa;
import com.fiap.pj.core.pecainsumo.domain.PecaInsumo;
import com.fiap.pj.core.pecainsumo.util.factory.PecaInsumoTestFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.fiap.pj.core.pecainsumo.util.factory.PecaInsumoTestFactory.DESCRICAO;
import static com.fiap.pj.core.pecainsumo.util.factory.PecaInsumoTestFactory.MODELO_VEICULO;
import static com.fiap.pj.core.pecainsumo.util.factory.PecaInsumoTestFactory.QUANTIDADE_ESTOQUE;
import static com.fiap.pj.core.pecainsumo.util.factory.PecaInsumoTestFactory.QUANTIDADE_MINIMO_ESTOQUE;
import static com.fiap.pj.core.pecainsumo.util.factory.PecaInsumoTestFactory.VALOR_UNITARIO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CriarPecaInsumoServiceTest {

    @Mock
    private PecaInsumoRepositoryJpa pecaInsumoRepositoryJpa;

    @InjectMocks
    private CriarPecaInsumoService criarPecaInsumoService;

    @Test
    void deveCriarPecaInsumo() {
        when(pecaInsumoRepositoryJpa.save(any(PecaInsumo.class))).thenReturn(PecaInsumoTestFactory.umPecaInsumo());

        var pecaInsumo = criarPecaInsumoService.handle(PecaInsumoTestFactory.umCriarPecaInsumoCommand());

        assertNotNull(pecaInsumo);
        assertEquals(MODELO_VEICULO, pecaInsumo.getModeloVeiculo());
        assertEquals(DESCRICAO, pecaInsumo.getDescricao());
        assertEquals(VALOR_UNITARIO, pecaInsumo.getValorUnitario());
        assertEquals(QUANTIDADE_ESTOQUE, pecaInsumo.getQuantidadeEstoque());
        assertEquals(QUANTIDADE_MINIMO_ESTOQUE, pecaInsumo.getQuantidadeMinimoEstoque());
    }
} 