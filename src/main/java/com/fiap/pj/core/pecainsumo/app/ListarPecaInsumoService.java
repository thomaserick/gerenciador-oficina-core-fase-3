package com.fiap.pj.core.pecainsumo.app;

import com.fiap.pj.core.pecainsumo.adapter.in.api.request.ListarPecaInsumoRequest;
import com.fiap.pj.core.pecainsumo.adapter.in.api.response.PecaInsumoResponse;
import com.fiap.pj.core.pecainsumo.domain.PecaInsumoDomainRepository;
import com.fiap.pj.core.pecainsumo.usecase.ListarPecaInsumoUseCase;
import com.fiap.pj.infra.sk.api.Slice;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ListarPecaInsumoService implements ListarPecaInsumoUseCase {

    private final PecaInsumoDomainRepository repository;

    @Override
    public Slice<PecaInsumoResponse> handle(ListarPecaInsumoRequest filterRequest) {
        return repository.findProjectedBy(filterRequest.buildSpecification(), filterRequest.getPageable(), PecaInsumoResponse.class);
    }
} 