package com.fiap.pj.core.ordemservico.app;


import com.fiap.pj.core.ordemservico.adapter.out.db.OrdemServicoRepositoryJpa;
import com.fiap.pj.core.ordemservico.domain.OrdemServico;
import com.fiap.pj.core.ordemservico.domain.enums.OrdemServicoStatus;
import com.fiap.pj.core.ordemservico.exception.OrdemServicoExceptions.OrdemServicoStatusInvalidoAguardandoRetiradaException;
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

import static com.fiap.pj.core.ordemservico.domain.enums.OrdemServicoStatus.CRIADA;
import static com.fiap.pj.core.ordemservico.domain.enums.OrdemServicoStatus.FINALIZADA;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MoverAguardandoRetiradaServiceTest {

    @Captor
    ArgumentCaptor<OrdemServico> ordemServicoArgumentCaptor;

    @Mock
    private OrdemServicoRepositoryJpa ordemServicoRepositoryJpa;

    @InjectMocks
    private MoverAguardandoRetiradaService moverAguardandoRetiradaService;

    @Test
    void deveAlteraStatusOsAguardandoRetirada() {
        TestSecurityConfig.setAuthentication();
        var ordemServico = OrdemServicoTestFactory.umaOrdemServico(FINALIZADA);

        when(ordemServicoRepositoryJpa.findByIdOrThrowNotFound(ordemServico.getId())).thenReturn(ordemServico);

        moverAguardandoRetiradaService.handle(ordemServico.getId());

        verify(ordemServicoRepositoryJpa).save(ordemServicoArgumentCaptor.capture());
        OrdemServico ordemServicoAlterada = ordemServicoArgumentCaptor.getValue();

        Assertions.assertNotNull(ordemServico);
        Assertions.assertEquals(OrdemServicoStatus.AGUARDANDO_RETIRADA, ordemServicoAlterada.getStatus());

    }

    @Test
    void deveRetornarOrdemServicoStatusInvalidoAguardandoRetiradaException() {
        var ordemServico = OrdemServicoTestFactory.umaOrdemServico(CRIADA);
        when(ordemServicoRepositoryJpa.findByIdOrThrowNotFound(ordemServico.getId())).thenReturn(ordemServico);

        var thrown = catchThrowable(() -> moverAguardandoRetiradaService.handle(ordemServico.getId()));
        assertThat(thrown).isInstanceOf(OrdemServicoStatusInvalidoAguardandoRetiradaException.class);
    }

}
