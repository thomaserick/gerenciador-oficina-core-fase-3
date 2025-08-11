package com.fiap.pj.core.ordemservico.app;


import com.fiap.pj.core.ordemservico.adapter.out.db.OrdemServicoRepositoryJpa;
import com.fiap.pj.core.ordemservico.domain.OrdemServico;
import com.fiap.pj.core.ordemservico.domain.enums.OrdemServicoStatus;
import com.fiap.pj.core.ordemservico.exception.OrdemServicoExceptions.OrdemServicoStatusInvalidoDiagnosticoException;
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

import static com.fiap.pj.core.ordemservico.domain.enums.OrdemServicoStatus.AGUARDANDO_APROVACAO;
import static com.fiap.pj.core.ordemservico.domain.enums.OrdemServicoStatus.EM_DIAGNOSTICO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RealizarDiagnosticoServiceTest {

    @Captor
    ArgumentCaptor<OrdemServico> ordemServicoArgumentCaptor;

    @Mock
    private OrdemServicoRepositoryJpa ordemServicoRepositoryJpa;

    @InjectMocks
    private RealizarDiagnosticoService realizarDiagnosticoService;

    @Test
    void deveAlteraStatusOsEmDiagnostico() {
        TestSecurityConfig.setAuthentication();
        var ordemServico = OrdemServicoTestFactory.umaOrdemServico(EM_DIAGNOSTICO);

        when(ordemServicoRepositoryJpa.findByIdOrThrowNotFound(ordemServico.getId())).thenReturn(ordemServico);

        realizarDiagnosticoService.handle(OrdemServicoTestFactory.umRealizarDiagnosticoOrdemServicoCommand());

        verify(ordemServicoRepositoryJpa).save(ordemServicoArgumentCaptor.capture());
        OrdemServico ordemServicoAlterada = ordemServicoArgumentCaptor.getValue();

        Assertions.assertNotNull(ordemServico);
        Assertions.assertEquals(OrdemServicoStatus.EM_DIAGNOSTICO, ordemServicoAlterada.getStatus());
        Assertions.assertEquals(OrdemServicoTestFactory.DESCRICAO, ordemServicoAlterada.getDiagnostico().getDescricao());

    }

    @Test
    void deveRetornarOrdemServicoStatusInvalidoDiagnosticoException() {
        var ordemServico = OrdemServicoTestFactory.umaOrdemServico(AGUARDANDO_APROVACAO);
        when(ordemServicoRepositoryJpa.findByIdOrThrowNotFound(ordemServico.getId())).thenReturn(ordemServico);

        var thrown = catchThrowable(() -> realizarDiagnosticoService.handle(OrdemServicoTestFactory.umRealizarDiagnosticoOrdemServicoCommand()));
        assertThat(thrown).isInstanceOf(OrdemServicoStatusInvalidoDiagnosticoException.class);
    }

}
