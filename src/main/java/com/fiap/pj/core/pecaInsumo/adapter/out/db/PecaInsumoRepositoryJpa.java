package com.fiap.pj.core.pecaInsumo.adapter.out.db;

import com.fiap.pj.core.pecaInsumo.domain.PecaInsumo;
import com.fiap.pj.core.pecaInsumo.domain.PecaInsumoDomainRepository;
import com.fiap.pj.core.pecaInsumo.exception.PecaInsumoExceptions.PecaInsumoNaoEncontradoException;
import com.fiap.pj.infra.jpa.ExtendedRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

import java.util.UUID;

public interface PecaInsumoRepositoryJpa extends PecaInsumoDomainRepository, Repository<PecaInsumo, UUID>, ExtendedRepository<PecaInsumo, UUID>, JpaSpecificationExecutor<PecaInsumo> {

    @Override
    default PecaInsumo findByIdOrThrowNotFound(UUID id) {
        return findById(id).orElseThrow(PecaInsumoNaoEncontradoException::new);
    }
} 