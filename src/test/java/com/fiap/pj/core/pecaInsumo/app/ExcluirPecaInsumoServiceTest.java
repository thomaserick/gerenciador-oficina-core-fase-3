package com.fiap.pj.core.pecaInsumo.app;

import com.fiap.pj.core.pecaInsumo.adapter.out.db.PecaInsumoRepositoryJpa;
import com.fiap.pj.core.pecaInsumo.domain.PecaInsumo;
import com.fiap.pj.core.pecaInsumo.usecase.command.ExcluirPecaInsumoCommand;
import com.fiap.pj.core.pecaInsumo.util.factory.PecaInsumoTestFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExcluirPecaInsumoServiceTest {

    @Mock
    private PecaInsumoRepositoryJpa pecaInsumoRepositoryJpa;

    @InjectMocks
    private ExcluirPecaInsumoService excluirPecaInsumoService;

    @Test
    void deveExcluirPecaInsumo() {
        var id = UUID.randomUUID();
        var pecaInsumo = PecaInsumoTestFactory.onePecaInsumo();
        var command = new ExcluirPecaInsumoCommand(id);

        when(pecaInsumoRepositoryJpa.findByIdOrThrowNotFound(id)).thenReturn(pecaInsumo);

        excluirPecaInsumoService.handle(command);

        verify(pecaInsumoRepositoryJpa).findByIdOrThrowNotFound(id);
        verify(pecaInsumoRepositoryJpa).delete(pecaInsumo);
    }
} 