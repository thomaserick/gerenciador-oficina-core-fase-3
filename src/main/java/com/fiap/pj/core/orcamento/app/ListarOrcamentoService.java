package com.fiap.pj.core.orcamento.app;


import com.fiap.pj.core.orcamento.adapter.in.api.request.ListarOrcamentoRequest;
import com.fiap.pj.core.orcamento.adapter.in.api.response.OrcamentoResponse;
import com.fiap.pj.core.orcamento.domain.OrcamentoDomainRepository;
import com.fiap.pj.core.orcamento.usecase.ListarOrcamentoUseCase;
import com.fiap.pj.infra.api.Slice;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class ListarOrcamentoService implements ListarOrcamentoUseCase {

    private final OrcamentoDomainRepository repository;

    @Override
    public Slice<OrcamentoResponse> handle(ListarOrcamentoRequest request) {
        var result = repository.findAllByClienteId(request.getClienteId(), request.getPageable());
        return new Slice<>(result.hasNext(), result.getContent());
    }
}
