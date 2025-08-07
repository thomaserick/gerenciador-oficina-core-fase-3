package com.fiap.pj.core.orcamento.domain;


import com.fiap.pj.core.orcamento.adapter.in.api.response.OrcamentoResponse;
import com.fiap.pj.infra.jpa.BaseCrudRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.UUID;

public interface OrcamentoDomainRepository extends BaseCrudRepository<Orcamento, UUID> {


    Slice<OrcamentoResponse> findAllByClienteId(UUID clienteId, Pageable pageable);

    @Override
    Orcamento findByIdOrThrowNotFound(UUID id);

}
