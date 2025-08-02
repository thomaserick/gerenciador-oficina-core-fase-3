package com.fiap.pj.core.servico.app;


import com.fiap.pj.core.servico.adapter.out.db.ServicoRepositoryJpa;
import com.fiap.pj.core.servico.domain.Servico;
import com.fiap.pj.core.servico.exception.ServicoExceptions.ServicoComRelacionamentoException;
import com.fiap.pj.core.servico.usecase.command.ExcluirServicoCommand;
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
class ExcluirServicoServiceTest {

    @Mock
    private ServicoRepositoryJpa servicoRepositoryJpa;

    @InjectMocks
    private ExcluirServicoService excluirServicoService;


    @Test
    void deveExcluirServico() {
        var id = UUID.randomUUID();
        when(servicoRepositoryJpa.findByIdOrThrowNotFound(id)).thenReturn(ServicoTestFactory.umServico());
        excluirServicoService.handle(new ExcluirServicoCommand(id));
        verify(servicoRepositoryJpa).delete(Mockito.any(Servico.class));
    }

    @Test
    void deveRetornarServicoComRelacionamentoException() {
        var id = UUID.randomUUID();
        when(servicoRepositoryJpa.findByIdOrThrowNotFound(id)).thenReturn(ServicoTestFactory.umServico());

        Mockito.doThrow(new ServicoComRelacionamentoException())
                .when(servicoRepositoryJpa)
                .delete(Mockito.any(Servico.class));

        var thrown = catchThrowable(() -> excluirServicoService.handle(new ExcluirServicoCommand(id)));
        assertThat(thrown).isInstanceOf(ServicoComRelacionamentoException.class);
    }

}
