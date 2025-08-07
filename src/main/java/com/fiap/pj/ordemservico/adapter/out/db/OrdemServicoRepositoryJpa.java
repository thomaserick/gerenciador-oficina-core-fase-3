package com.fiap.pj.ordemservico.adapter.out.db;


import com.fiap.pj.ordemservico.domain.OrdemServico;
import com.fiap.pj.ordemservico.domain.OrdemServicoDomainRepository;
import com.fiap.pj.ordemservico.exception.OrdemServicoExceptions.OrdemServicoEncontradoException;
import org.springframework.data.repository.Repository;

import java.util.UUID;


public interface OrdemServicoRepositoryJpa extends OrdemServicoDomainRepository, Repository<OrdemServico, UUID> {

    @Override
    default OrdemServico findByIdOrThrowNotFound(UUID id) {
        return findById(id).orElseThrow(OrdemServicoEncontradoException::new);
    }
}