package com.fiap.pj.core.orcamento.app;


import com.fiap.pj.core.orcamento.adapter.out.db.OrcamentoRepositoryJpa;
import com.fiap.pj.core.orcamento.domain.Orcamento;
import com.fiap.pj.core.orcamento.exception.OrcamentoExceptions.ReprovarOrcamentoStatusInvalidoException;
import com.fiap.pj.core.orcamento.util.factory.OrcamentoTestFactory;
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
class ReprovarOrcamentoServicoTest {

    @Captor
    ArgumentCaptor<Orcamento> orcamentoArgumentCaptor;

    @Mock
    private OrcamentoRepositoryJpa orcamentoRepositoryJpa;


    @InjectMocks
    private ReprovarOrcamentoService reprovarOrcamentoService;

    @Test
    void deveReprovarOrcamento() {
        var orcamento = OrcamentoTestFactory.umOrcamento();
        orcamento.adicionarServico(OrcamentoTestFactory.umOrcamentoItemServico(orcamento.getId()));
        when(orcamentoRepositoryJpa.findByIdOrThrowNotFound(any(UUID.class))).thenReturn(orcamento);

        reprovarOrcamentoService.handle(OrcamentoTestFactory.umReprovarOrcamentoCommand());

        verify(orcamentoRepositoryJpa).save(orcamentoArgumentCaptor.capture());
        Orcamento orcamentoAlterado = orcamentoArgumentCaptor.getValue();

        Assertions.assertTrue(orcamentoAlterado.getStatus().isReprovado());
    }

    @Test
    void deveRetornarStatusOrcamentoNaoPermiteReprovarException() {
        var orcamento = OrcamentoTestFactory.umOrcamentoAprovado();
        when(orcamentoRepositoryJpa.findByIdOrThrowNotFound(any(UUID.class))).thenReturn(orcamento);

        var thrown = catchThrowable(() -> reprovarOrcamentoService.handle(OrcamentoTestFactory.umReprovarOrcamentoCommand()));
        assertThat(thrown).isInstanceOf(ReprovarOrcamentoStatusInvalidoException.class);

    }


}
