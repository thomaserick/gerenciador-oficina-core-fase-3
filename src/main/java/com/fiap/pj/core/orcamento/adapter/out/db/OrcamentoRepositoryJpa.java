package com.fiap.pj.core.orcamento.adapter.out.db;


import com.fiap.pj.core.orcamento.domain.Orcamento;
import com.fiap.pj.core.orcamento.domain.OrcamentoDomainRepository;
import com.fiap.pj.core.orcamento.exception.OrcamentoExceptions.OrcamentoNaoEncontradoException;
import org.springframework.data.repository.Repository;

import java.util.UUID;


public interface OrcamentoRepositoryJpa extends OrcamentoDomainRepository, Repository<Orcamento, UUID> {


    @Override
    default Orcamento findByIdOrThrowNotFound(UUID id) {
        return findById(id).orElseThrow(OrcamentoNaoEncontradoException::new);
    }

}