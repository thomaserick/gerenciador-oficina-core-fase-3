package com.fiap.pj.core.pecainsumo.app;

import com.fiap.pj.core.pecainsumo.adapter.out.db.PecaInsumoRepositoryJpa;
import com.fiap.pj.core.pecainsumo.domain.PecaInsumo;
import com.fiap.pj.core.pecainsumo.util.factory.PecaInsumoTestFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static com.fiap.pj.core.pecainsumo.util.factory.PecaInsumoTestFactory.DESCRICAO_ALTERADA;
import static com.fiap.pj.core.pecainsumo.util.factory.PecaInsumoTestFactory.MODELO_VEICULO_ALTERADO;
import static com.fiap.pj.core.pecainsumo.util.factory.PecaInsumoTestFactory.QUANTIDADE_MINIMO_ESTOQUE_ALTERADO;
import static com.fiap.pj.core.pecainsumo.util.factory.PecaInsumoTestFactory.VALOR_UNITARIO_ALTERADO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AlterarPecaInsumoServiceTest {


    @Captor
    ArgumentCaptor<PecaInsumo> pecaInsumoArgumentCaptor;

    @Mock
    private PecaInsumoRepositoryJpa pecaInsumoRepositoryJpa;

    @InjectMocks
    private AlterarPecaInsumoService alterarPecaInsumoService;

    @Test
    void deveAlterarPecaInsumo() {
        var id = UUID.randomUUID();
        var pecaInsumo = PecaInsumoTestFactory.umPecaInsumo();
        var command = PecaInsumoTestFactory.umAlterarPecaInsumoCommand(id);

        when(pecaInsumoRepositoryJpa.findByIdOrThrowNotFound(any(UUID.class))).thenReturn(pecaInsumo);

        alterarPecaInsumoService.handle(command);

        verify(pecaInsumoRepositoryJpa).save(pecaInsumoArgumentCaptor.capture());
        PecaInsumo pecaInsumoAlterado = pecaInsumoArgumentCaptor.getValue();


        assertEquals(MODELO_VEICULO_ALTERADO, pecaInsumoAlterado.getModeloVeiculo());
        assertEquals(DESCRICAO_ALTERADA, pecaInsumoAlterado.getDescricao());
        assertEquals(VALOR_UNITARIO_ALTERADO, pecaInsumoAlterado.getValorUnitario());
        assertEquals(QUANTIDADE_MINIMO_ESTOQUE_ALTERADO, pecaInsumoAlterado.getQuantidadeMinimoEstoque());


        verify(pecaInsumoRepositoryJpa).save(pecaInsumo);
    }
} 