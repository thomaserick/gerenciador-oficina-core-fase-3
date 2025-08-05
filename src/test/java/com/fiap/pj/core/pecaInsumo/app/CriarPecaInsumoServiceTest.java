package com.fiap.pj.core.pecaInsumo.app;

import com.fiap.pj.core.pecaInsumo.adapter.out.db.PecaInsumoRepositoryJpa;
import com.fiap.pj.core.pecaInsumo.domain.PecaInsumo;
import com.fiap.pj.core.pecaInsumo.util.factory.PecaInsumoTestFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static com.fiap.pj.core.pecaInsumo.util.factory.PecaInsumoTestFactory.*;
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
        when(pecaInsumoRepositoryJpa.save(any(PecaInsumo.class))).thenReturn(PecaInsumoTestFactory.onePecaInsumo());

        var pecaInsumo = criarPecaInsumoService.handle(PecaInsumoTestFactory.oneCreatePecaInsumoCommand());

        assertNotNull(pecaInsumo);
        assertEquals(NOME, pecaInsumo.getNome());
        assertEquals(DESCRICAO, pecaInsumo.getDescricao());
        assertEquals(VALOR_UNITARIO, pecaInsumo.getValorUnitario());
        assertEquals(QUANTIDADE_ESTOQUE, pecaInsumo.getQuantidadeEstoque());
        assertEquals(QUANTIDADE_MINIMO_ESTOQUE, pecaInsumo.getQuantidadeMinimoEstoque());
    }
} 