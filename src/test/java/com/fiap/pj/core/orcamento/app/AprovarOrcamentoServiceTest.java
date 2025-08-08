package com.fiap.pj.core.orcamento.app;


import com.fiap.pj.core.orcamento.adapter.out.db.OrcamentoRepositoryJpa;
import com.fiap.pj.core.orcamento.domain.Orcamento;
import com.fiap.pj.core.orcamento.domain.enums.OrcamentoStatus;
import com.fiap.pj.core.orcamento.exception.OrcamentoExceptions.AlterarOrcamentoStatusInvalidoException;
import com.fiap.pj.core.orcamento.util.factory.OrcamentoTestFactory;
import com.fiap.pj.core.ordemservico.usecase.CriarOrdemServicoUseCase;
import com.fiap.pj.core.ordemservico.usecase.command.CriarOrdemServicoCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AprovarOrcamentoServiceTest {

    @Captor
    ArgumentCaptor<Orcamento> orcamentoArgumentCaptor;

    @Mock
    private OrcamentoRepositoryJpa orcamentoRepositoryJpa;

    @Mock
    private CriarOrdemServicoUseCase criarOrdemServicoUseCase;


    @InjectMocks
    private AprovarOrcamentoService aprovarOrcamentoService;

    @Test
    void deveAprovarOrcamento() {
        var orcamento = OrcamentoTestFactory.umOrcamento();
        orcamento.adicionarServico(OrcamentoTestFactory.umOrcamentoItemServico(orcamento.getId()));
        when(orcamentoRepositoryJpa.findByIdOrThrowNotFound(any(UUID.class))).thenReturn(orcamento);
        when(criarOrdemServicoUseCase.handle(any(CriarOrdemServicoCommand.class))).thenReturn(UUID.randomUUID());

        aprovarOrcamentoService.handle(OrcamentoTestFactory.umAprovarOrcamentoCommand());

        verify(orcamentoRepositoryJpa).save(orcamentoArgumentCaptor.capture());
        Orcamento orcamentoAlterado = orcamentoArgumentCaptor.getValue();

        Assertions.assertTrue(orcamentoAlterado.getStatus().isAprovado());
    }

    @Test
    void deveRetornarStatusOrcamentoNaoPermiteAprovarException() {
        var orcamento = OrcamentoTestFactory.umOrcamento(OrcamentoStatus.REPROVADO);
        when(orcamentoRepositoryJpa.findByIdOrThrowNotFound(any(UUID.class))).thenReturn(orcamento);

        var thrown = catchThrowable(() -> aprovarOrcamentoService.handle(OrcamentoTestFactory.umAprovarOrcamentoCommand()));
        assertThat(thrown).isInstanceOf(AlterarOrcamentoStatusInvalidoException.class);

    }


}
