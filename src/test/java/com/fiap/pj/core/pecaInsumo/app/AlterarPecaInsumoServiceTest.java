package com.fiap.pj.core.pecaInsumo.app;

import com.fiap.pj.core.pecaInsumo.adapter.out.db.PecaInsumoRepositoryJpa;
import com.fiap.pj.core.pecaInsumo.domain.PecaInsumo;
import com.fiap.pj.core.pecaInsumo.util.factory.PecaInsumoTestFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static com.fiap.pj.core.pecaInsumo.util.factory.PecaInsumoTestFactory.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AlterarPecaInsumoServiceTest {

    @Mock
    private PecaInsumoRepositoryJpa pecaInsumoRepositoryJpa;

    @InjectMocks
    private AlterarPecaInsumoService alterarPecaInsumoService;

    @Test
    void deveAlterarPecaInsumo() {
        var id = UUID.randomUUID();
        var pecaInsumo = PecaInsumoTestFactory.onePecaInsumo();
        var command = PecaInsumoTestFactory.umUpdatePecaInsumoCommand(id);
        command.setId(id);

        when(pecaInsumoRepositoryJpa.findByIdOrThrowNotFound(id)).thenReturn(pecaInsumo);
        when(pecaInsumoRepositoryJpa.save(any(PecaInsumo.class))).thenReturn(pecaInsumo);

        alterarPecaInsumoService.handle(command);

        verify(pecaInsumoRepositoryJpa).findByIdOrThrowNotFound(id);
        verify(pecaInsumoRepositoryJpa).save(pecaInsumo);
        assertEquals(ALTER_NOME, pecaInsumo.getNome());
        assertEquals(ALTER_DESCRICAO, pecaInsumo.getDescricao());
        assertEquals(ALTER_VALOR_UNITARIO, pecaInsumo.getValorUnitario());
    }
} 