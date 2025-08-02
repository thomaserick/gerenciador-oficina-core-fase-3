package com.fiap.pj.core.servico.app;


import com.fiap.pj.core.servico.adapter.out.db.ServicoRepositoryJpa;
import com.fiap.pj.core.servico.domain.Servico;
import com.fiap.pj.core.servico.util.factory.ServicoTestFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CriarServicoServiceTest {

    @Mock
    private ServicoRepositoryJpa servicoRepositoryJpa;

    @InjectMocks
    private CriarServicoService criarServicoService;

    @Test
    void deveCriarServico() {
        when(servicoRepositoryJpa.save(any(Servico.class))).thenReturn(ServicoTestFactory.umServico());

        var service = criarServicoService.handle(ServicoTestFactory.umCriarServicoCommand());

        assertNotNull(service);
        assertEquals(ServicoTestFactory.ID, service.getId());
        assertEquals(ServicoTestFactory.DESCRICAO, service.getDescricao());
        assertEquals(ServicoTestFactory.PRECO, service.getPreco());
        assertEquals(ServicoTestFactory.OBSERVACAO, service.getObservacao());

    }
}
