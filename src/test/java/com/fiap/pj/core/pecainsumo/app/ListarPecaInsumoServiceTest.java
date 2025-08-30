package com.fiap.pj.core.pecainsumo.app;

import com.fiap.pj.core.pecainsumo.adapter.in.api.request.ListarPecaInsumoRequest;
import com.fiap.pj.core.pecainsumo.adapter.in.api.response.PecaInsumoResponse;
import com.fiap.pj.core.pecainsumo.adapter.out.db.PecaInsumoRepositoryJpa;
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
class ListarPecaInsumoServiceTest {

    @Mock
    private PecaInsumoRepositoryJpa pecaInsumoRepositoryJpa;

    @InjectMocks
    private ListarPecaInsumoService listarPecaInsumoService;

    @Test
    void deveListarPecasInsumos() {
        var request = new ListarPecaInsumoRequest("fusca", "motor", null, PageRequest.of(0, 10));

        var slice = new Slice<PecaInsumoResponse>(false, List.of());

        when(pecaInsumoRepositoryJpa.findProjectedBy(any(Specification.class), any(PageRequest.class), any(Class.class)))
                .thenReturn(slice);

        var result = listarPecaInsumoService.handle(request);

        assertNotNull(result);
        assertEquals(0, result.getItems().size());
    }

    @Test
    void deveListarPecasInsumosComEstoqueBaixo() {
        var request = new ListarPecaInsumoRequest("fusca", "motor", true, PageRequest.of(0, 10));

        var slice = new Slice<PecaInsumoResponse>(false, List.of());

        when(pecaInsumoRepositoryJpa.findProjectedBy(any(Specification.class), any(PageRequest.class), any(Class.class)))
                .thenReturn(slice);

        var result = listarPecaInsumoService.handle(request);

        assertNotNull(result);
        assertEquals(0, result.getItems().size());
    }
} 