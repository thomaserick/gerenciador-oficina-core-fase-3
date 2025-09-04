package com.fiap.pj.core.ordemservico.app;


import com.fiap.pj.core.ordemservico.app.gateways.OrdemServicoGateway;
import com.fiap.pj.core.ordemservico.domain.OrdemServico;
import com.fiap.pj.core.ordemservico.domain.enums.OrdemServicoStatus;
import com.fiap.pj.core.ordemservico.exception.OrdemServicoExceptions.OrdemServicoNaoEncontradaException;
import com.fiap.pj.core.ordemservico.exception.OrdemServicoExceptions.OrdemServicoStatusInvalidoEmExecucaoException;
import com.fiap.pj.core.ordemservico.util.factory.OrdemServicoTestFactory;
import com.fiap.pj.util.TestSecurityConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static com.fiap.pj.core.ordemservico.domain.enums.OrdemServicoStatus.AGUARDANDO_APROVACAO;
import static com.fiap.pj.core.ordemservico.domain.enums.OrdemServicoStatus.CRIADA;
import static com.fiap.pj.core.ordemservico.domain.enums.OrdemServicoStatus.EM_DIAGNOSTICO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MoverEmExecucaoServiceTest {

    @Captor
    ArgumentCaptor<OrdemServico> ordemServicoArgumentCaptor;

    @Mock
    private OrdemServicoGateway ordemServicoGateway;

    @InjectMocks
    private MoverEmExecucaoUseCaseImpl overEmExecucaoUseCaseImpl;

    @Test
    void deveAlteraStatusOsEmExecucao() {
        TestSecurityConfig.setAuthentication();
        var ordemServico = OrdemServicoTestFactory.umaOrdemServico(AGUARDANDO_APROVACAO);

        when(ordemServicoGateway.buscarPorId(ordemServico.getId())).thenReturn(Optional.of(ordemServico));

        overEmExecucaoUseCaseImpl.handle(ordemServico.getId());

        verify(ordemServicoGateway).salvar(ordemServicoArgumentCaptor.capture());
        OrdemServico ordemServicoAlterada = ordemServicoArgumentCaptor.getValue();

        Assertions.assertNotNull(ordemServico);
        Assertions.assertEquals(OrdemServicoStatus.EM_EXECUCAO, ordemServicoAlterada.getStatus());

    }

    @Test
    void deveAlteraStatusOsEmExecucaoComSatusAtualEmDiagnostico() {
        TestSecurityConfig.setAuthentication();
        var ordemServico = OrdemServicoTestFactory.umaOrdemServico(EM_DIAGNOSTICO);

        when(ordemServicoGateway.buscarPorId(ordemServico.getId())).thenReturn(Optional.of(ordemServico));

        overEmExecucaoUseCaseImpl.handle(ordemServico.getId());

        verify(ordemServicoGateway).salvar(ordemServicoArgumentCaptor.capture());
        OrdemServico ordemServicoAlterada = ordemServicoArgumentCaptor.getValue();

        Assertions.assertNotNull(ordemServico);
        Assertions.assertEquals(OrdemServicoStatus.EM_EXECUCAO, ordemServicoAlterada.getStatus());

    }

    @Test
    void deveRetornarOrdemServicoNaoEncontradaException() {
        var id = UUID.randomUUID();

        Mockito.doThrow(new OrdemServicoNaoEncontradaException())
                .when(ordemServicoGateway)
                .buscarPorId(id);

        var thrown = catchThrowable(() -> overEmExecucaoUseCaseImpl.handle(id));
        assertThat(thrown).isInstanceOf(OrdemServicoNaoEncontradaException.class);
    }

    @Test
    void deveRetornarOrdemServicoStatusInvalidoEmExecucaoException() {
        var ordemServico = OrdemServicoTestFactory.umaOrdemServico(CRIADA);
        when(ordemServicoGateway.buscarPorId(ordemServico.getId())).thenReturn(Optional.of(ordemServico));

        var thrown = catchThrowable(() -> overEmExecucaoUseCaseImpl.handle(ordemServico.getId()));
        assertThat(thrown).isInstanceOf(OrdemServicoStatusInvalidoEmExecucaoException.class);
    }

}
