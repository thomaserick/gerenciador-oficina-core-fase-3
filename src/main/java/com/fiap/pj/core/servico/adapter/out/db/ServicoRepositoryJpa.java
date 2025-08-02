package com.fiap.pj.core.servico.adapter.out.db;


import com.fiap.pj.core.servico.domain.Servico;
import com.fiap.pj.core.servico.domain.ServicoDomainRepository;
import com.fiap.pj.core.servico.exception.ServicoExceptions.ServiceNotFoundException;
import org.springframework.data.repository.Repository;

import java.util.UUID;


public interface ServicoRepositoryJpa extends ServicoDomainRepository, Repository<Servico, UUID> {

    @Override
    default Servico findByIdOrThrowNotFound(UUID id) {
        return findById(id).orElseThrow(ServiceNotFoundException::new);
    }

}