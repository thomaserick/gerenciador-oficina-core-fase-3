package com.fiap.pj.core.ordemservico.app;

import com.fiap.pj.core.ordemservico.adapter.in.api.request.BuscarAcompanhamentoByOrdemServicoIdRequest;
import com.fiap.pj.core.ordemservico.adapter.in.api.response.AcompanhamentoOrdemServicoResponse;
import com.fiap.pj.core.ordemservico.domain.OrdemServicoDomainRepository;
import com.fiap.pj.core.ordemservico.usecase.BuscarAcompanhamentoByOrdemServicoIdUseCase;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class BuscarAcompanhamentoByByOrdemServicoIdIdService implements BuscarAcompanhamentoByOrdemServicoIdUseCase {

    private final OrdemServicoDomainRepository repository;

    @Override
    public Optional<AcompanhamentoOrdemServicoResponse> handle(BuscarAcompanhamentoByOrdemServicoIdRequest request) {
        return this.repository.findById(request.getId(), AcompanhamentoOrdemServicoResponse.class);
    }
}
