package com.fiap.pj.core.ordemservico.adapter.out.db;


import com.fiap.pj.core.ordemservico.adapter.in.api.response.AcompanhamentoOrdemServicoResponse;
import com.fiap.pj.core.ordemservico.domain.OrdemServico;
import com.fiap.pj.core.ordemservico.domain.OrdemServicoDomainRepository;
import com.fiap.pj.core.ordemservico.exception.OrdemServicoExceptions.OrdemServicoNaoEncontradaException;
import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.UUID;


public interface OrdemServicoRepositoryJpa extends OrdemServicoDomainRepository, Repository<OrdemServico, UUID> {


    @Override
    default OrdemServico findByIdOrThrowNotFound(UUID id) {
        return findById(id).orElseThrow(OrdemServicoNaoEncontradaException::new);
    }

    @Override
    Optional<AcompanhamentoOrdemServicoResponse> findById(UUID id,
                                                          Class<AcompanhamentoOrdemServicoResponse> type);
}