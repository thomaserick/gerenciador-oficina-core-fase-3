package com.fiap.pj.ordemservico.domain;

import com.fiap.pj.infra.jpa.BaseCrudRepository;

import java.util.UUID;

public interface OrdemServicoDomainRepository extends BaseCrudRepository<OrdemServico, UUID> {


    @Override
    OrdemServico findByIdOrThrowNotFound(UUID id);
}