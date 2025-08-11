package com.fiap.pj.core.ordemservico.app;


import com.fiap.pj.core.ordemservico.adapter.out.db.OrdemServicoRepositoryJpa;
import com.fiap.pj.core.ordemservico.domain.OrdemServico;
import com.fiap.pj.core.ordemservico.domain.enums.OrdemServicoStatus;
import com.fiap.pj.core.ordemservico.exception.OrdemServicoExceptions.OrdemServicoStatusInvalidoEntregueException;
import com.fiap.pj.core.ordemservico.util.factory.OrdemServicoTestFactory;
import com.fiap.pj.util.TestSecurityConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.fiap.pj.core.ordemservico.domain.enums.OrdemServicoStatus.AGUARDANDO_RETIRADA;
import static com.fiap.pj.core.ordemservico.domain.enums.OrdemServicoStatus.CRIADA;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MoverEntregueServiceTest {

    @Captor
    ArgumentCaptor<OrdemServico> ordemServicoArgumentCaptor;

    @Mock
    private OrdemServicoRepositoryJpa ordemServicoRepositoryJpa;

    @InjectMocks
    private MoverEntregueService moverEntregueService;

    @Test
    void deveAlteraStatusOsEntregue() {
        TestSecurityConfig.setAuthentication();
        var ordemServico = OrdemServicoTestFactory.umaOrdemServico(AGUARDANDO_RETIRADA);

        when(ordemServicoRepositoryJpa.findByIdOrThrowNotFound(ordemServico.getId())).thenReturn(ordemServico);

        moverEntregueService.handle(ordemServico.getId());

        verify(ordemServicoRepositoryJpa).save(ordemServicoArgumentCaptor.capture());
        OrdemServico ordemServicoAlterada = ordemServicoArgumentCaptor.getValue();

        Assertions.assertNotNull(ordemServico);
        Assertions.assertEquals(OrdemServicoStatus.ENTREGUE, ordemServicoAlterada.getStatus());

    }

    @Test
    void deveRetornarOrdemServicoStatusInvalidoEntregueException() {
        var ordemServico = OrdemServicoTestFactory.umaOrdemServico(CRIADA);
        when(ordemServicoRepositoryJpa.findByIdOrThrowNotFound(ordemServico.getId())).thenReturn(ordemServico);

        var thrown = catchThrowable(() -> moverEntregueService.handle(ordemServico.getId()));
        assertThat(thrown).isInstanceOf(OrdemServicoStatusInvalidoEntregueException.class);
    }

}
