package com.fiap.pj.core.pecainsumo.adapter.out.db;

import com.fiap.pj.core.pecainsumo.domain.PecaInsumo;
import com.fiap.pj.core.pecainsumo.domain.PecaInsumoDomainRepository;
import com.fiap.pj.core.pecainsumo.exception.PecaInsumoExceptions.PecaInsumoNaoEncontradoException;
import com.fiap.pj.infra.jpa.ExtendedRepository;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.Repository;

import java.util.UUID;

public interface PecaInsumoRepositoryJpa extends PecaInsumoDomainRepository, Repository<PecaInsumo, UUID>, ExtendedRepository<PecaInsumo, UUID>, JpaSpecificationExecutor<PecaInsumo> {

    @Override
    default PecaInsumo findByIdOrThrowNotFound(UUID id) {
        return this.findById(id).orElseThrow(PecaInsumoNaoEncontradoException::new);
    }

    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    default PecaInsumo findByIdOrThrowNotFoundWithLocky(UUID id) {
        return this.findById(id).orElseThrow(PecaInsumoNaoEncontradoException::new);
    }
} 