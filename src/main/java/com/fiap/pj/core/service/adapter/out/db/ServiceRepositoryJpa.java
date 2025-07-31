package com.fiap.pj.core.service.adapter.out.db;


import com.fiap.pj.core.service.domain.ServiceDomainRepository;
import com.fiap.pj.core.service.domain.ServiceEntity;
import com.fiap.pj.core.service.exception.ServiceExceptions.ServiceNotFoundException;
import org.springframework.data.repository.Repository;

import java.util.UUID;


public interface ServiceRepositoryJpa extends ServiceDomainRepository, Repository<ServiceEntity, UUID> {

    @Override
    default ServiceEntity findByIdOrThrowNotFound(UUID id) {
        return findById(id).orElseThrow(ServiceNotFoundException::new);
    }

}