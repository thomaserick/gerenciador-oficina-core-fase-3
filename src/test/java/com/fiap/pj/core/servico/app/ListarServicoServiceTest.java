package com.fiap.pj.core.servico.app;

import com.fiap.pj.core.servico.adapter.in.api.request.ListarServicoRequest;
import com.fiap.pj.core.servico.adapter.in.api.response.ServicoResponse;
import com.fiap.pj.core.servico.adapter.out.db.ServicoRepositoryJpa;
import com.fiap.pj.infra.sk.api.Slice;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListarServicoServiceTest {

    @Mock
    private ServicoRepositoryJpa servicoRepositoryJpa;

    @InjectMocks
    private ListarServicoService listarServicoService;

    @Test
    void deveListarServicos() {
        var request = new ListarServicoRequest("Troca Óleo", PageRequest.of(0, 10), true);

        var slice = new Slice<ServicoResponse>(false, List.of());

        when(servicoRepositoryJpa.findProjectedBy(any(Specification.class), any(PageRequest.class), any(Class.class)))
                .thenReturn(slice);

        var result = listarServicoService.handle(request);

        assertNotNull(result);
        assertEquals(0, result.getItems().size());
    }

} 