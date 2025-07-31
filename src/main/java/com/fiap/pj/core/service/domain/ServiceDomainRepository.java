package com.fiap.pj.core.service.domain;


import com.fiap.pj.infra.jpa.BaseCrudRepository;

import java.util.UUID;

public interface ServiceDomainRepository extends BaseCrudRepository<ServiceEntity, UUID> {

}
