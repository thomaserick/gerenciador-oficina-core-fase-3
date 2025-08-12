package com.fiap.pj.core.ordemservico.domain;

import com.fiap.pj.core.ordemservico.adapter.in.api.response.AcompanhamentoOrdemServicoResponse;
import com.fiap.pj.infra.jpa.BaseCrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrdemServicoDomainRepository extends BaseCrudRepository<OrdemServico, UUID> {

    @Override
    OrdemServico findByIdOrThrowNotFound(UUID id);

    Optional<AcompanhamentoOrdemServicoResponse> findById(UUID id,
                                                          Class<AcompanhamentoOrdemServicoResponse> type);
}