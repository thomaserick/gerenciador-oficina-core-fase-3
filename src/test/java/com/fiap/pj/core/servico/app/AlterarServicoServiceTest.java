package com.fiap.pj.core.servico.app;


import com.fiap.pj.core.servico.adapter.out.db.ServicoRepositoryJpa;
import com.fiap.pj.core.servico.domain.Servico;
import com.fiap.pj.core.servico.exception.ServicoExceptions.ServicoNaoEncontradoException;
import com.fiap.pj.core.servico.exception.ServicoExceptions.StatusServicoNaoPermiteAlterarException;
import com.fiap.pj.core.servico.util.factory.ServicoTestFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static com.fiap.pj.core.servico.util.factory.ServicoTestFactory.DESCRICAO;
import static com.fiap.pj.core.servico.util.factory.ServicoTestFactory.ID;
import static com.fiap.pj.core.servico.util.factory.ServicoTestFactory.OBSERVACAO;
import static com.fiap.pj.core.servico.util.factory.ServicoTestFactory.PRECO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AlterarServicoServiceTest {

    @Captor
    ArgumentCaptor<Servico> servicoArgumentCaptor;
    @Mock
    private ServicoRepositoryJpa servicoRepositoryJpa;

    @InjectMocks
    private AlterarServicoService alterarServicoService;

    @Test
    void deveAlterarServico() {
        when(servicoRepositoryJpa.findByIdOrThrowNotFound(any(UUID.class))).thenReturn(ServicoTestFactory.umServico());

        alterarServicoService.handle(ServicoTestFactory.umAlterarServicoCommand(ID));

        verify(servicoRepositoryJpa).save(servicoArgumentCaptor.capture());
        Servico servicoAlterado = servicoArgumentCaptor.getValue();

        assertNotNull(servicoAlterado);
        assertEquals(ID, servicoAlterado.getId());
        assertEquals(ServicoTestFactory.DESCRICAO_ALTERADA, servicoAlterado.getDescricao());
        assertEquals(ServicoTestFactory.PRECO_ALTERADO, servicoAlterado.getPreco());
        assertEquals(ServicoTestFactory.OBSERVACAO_ALTERADA, servicoAlterado.getObservacao());

    }

    @Test
    void deveRetornarStatusServicoNaoPermiteAlterarException() {
        var servico = new Servico(ID, DESCRICAO, PRECO, OBSERVACAO, false);

        when(servicoRepositoryJpa.findByIdOrThrowNotFound(any(UUID.class))).thenReturn(servico);

        var thrown = catchThrowable(() -> alterarServicoService.handle(ServicoTestFactory.umAlterarServicoCommand(ID)));
        assertThat(thrown).isInstanceOf(StatusServicoNaoPermiteAlterarException.class);

    }

    @Test
    void deveRetornarServicoNaoEncontratoException() {
        var servico = ServicoTestFactory.umServico();

        Mockito.doThrow(new ServicoNaoEncontradoException())
                .when(servicoRepositoryJpa)
                .findByIdOrThrowNotFound(servico.getId());

        var thrown = catchThrowable(() -> alterarServicoService.handle(ServicoTestFactory.umAlterarServicoCommand(ID)));
        assertThat(thrown).isInstanceOf(ServicoNaoEncontradoException.class);

    }


}
