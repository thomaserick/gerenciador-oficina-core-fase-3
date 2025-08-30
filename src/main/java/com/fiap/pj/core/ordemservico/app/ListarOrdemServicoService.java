package com.fiap.pj.core.ordemservico.app;

import com.fiap.pj.core.ordemservico.adapter.in.api.request.ListarOrdemServicoRequest;
import com.fiap.pj.core.ordemservico.adapter.in.api.response.OrdemServicoResponse;
import com.fiap.pj.core.ordemservico.domain.OrdemServicoDomainRepository;
import com.fiap.pj.core.ordemservico.usecase.ListarOrdemServicoUseCase;
import com.fiap.pj.infra.sk.api.Slice;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class ListarOrdemServicoService implements ListarOrdemServicoUseCase {

    private final OrdemServicoDomainRepository repository;

    @Override
    public Slice<OrdemServicoResponse> handle(ListarOrdemServicoRequest request) {
        return this.repository.findProjectedBy(request.buildSpecification(), request.getPageable(), OrdemServicoResponse.class);
    }
}
