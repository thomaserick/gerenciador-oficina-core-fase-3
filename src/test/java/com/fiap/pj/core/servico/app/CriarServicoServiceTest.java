package com.fiap.pj.core.servico.app;


import com.fiap.pj.core.servico.adapter.out.db.ServicoRepositoryJpa;
import com.fiap.pj.core.servico.domain.Servico;
import com.fiap.pj.core.servico.util.factory.ServiceTestFactory;
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
        when(servicoRepositoryJpa.save(any(Servico.class))).thenReturn(ServiceTestFactory.oneService());

        var service = criarServicoService.handle(ServiceTestFactory.oneCreateServiceCommand());

        assertNotNull(service);
        assertEquals(ServiceTestFactory.ID, service.getId());
        assertEquals(ServiceTestFactory.DESCRICAO, service.getDescricao());
        assertEquals(ServiceTestFactory.PRECO, service.getPreco());
        assertEquals(ServiceTestFactory.OBSERVACAO, service.getObservacao());

    }
}
