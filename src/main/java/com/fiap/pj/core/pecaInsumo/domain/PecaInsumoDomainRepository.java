package com.fiap.pj.core.pecaInsumo.domain;

import com.fiap.pj.infra.jpa.BaseCrudRepository;

import java.util.UUID;

public interface PecaInsumoDomainRepository extends BaseCrudRepository<PecaInsumo, UUID> {

    @Override
    PecaInsumo findByIdOrThrowNotFound(UUID id);
} 