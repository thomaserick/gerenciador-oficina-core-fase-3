package com.fiap.pj.core.servico.app;


import com.fiap.pj.core.servico.adapter.out.db.ServicoRepositoryJpa;
import com.fiap.pj.core.servico.domain.Servico;
import com.fiap.pj.core.servico.exception.ServicoExceptions.ServicoNaoEncontradoException;
import com.fiap.pj.core.servico.usecase.command.InativarServicoCommand;
import com.fiap.pj.core.servico.util.factory.ServicoTestFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InativarServicoServiceTest {

    @Mock
    private ServicoRepositoryJpa servicoRepositoryJpa;

    @InjectMocks
    private InativarServicoService inativarServicoService;

    @Test
    void deveInativarServico() {
        var id = ServicoTestFactory.ID;
        when(servicoRepositoryJpa.findByIdOrThrowNotFound(id)).thenReturn(ServicoTestFactory.umServico());
        inativarServicoService.handle(new InativarServicoCommand(id));
        verify(servicoRepositoryJpa).save(Mockito.any(Servico.class));
    }

    @Test
    void deveRetonarServicoNaoEncontradoException() {
        var id = UUID.randomUUID();

        Mockito.doThrow(new ServicoNaoEncontradoException())
                .when(servicoRepositoryJpa)
                .findByIdOrThrowNotFound(id);

        var thrown = catchThrowable(() -> inativarServicoService.handle(new InativarServicoCommand(id)));
        assertThat(thrown).isInstanceOf(ServicoNaoEncontradoException.class);

    }

}
