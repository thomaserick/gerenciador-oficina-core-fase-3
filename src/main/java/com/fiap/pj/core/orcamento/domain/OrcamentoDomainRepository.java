package com.fiap.pj.core.orcamento.domain;


import com.fiap.pj.infra.sk.jpa.BaseCrudRepository;

import java.util.UUID;

public interface OrcamentoDomainRepository extends BaseCrudRepository<Orcamento, UUID> {


    @Override
    Orcamento findByIdOrThrowNotFound(UUID id);

}
