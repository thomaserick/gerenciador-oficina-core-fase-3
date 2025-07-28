package com.fiap.pj.core.customer.domain;


import com.fiap.pj.core.customer.exception.CustomerExceptions.CustomerNotFoundException;
import com.fiap.pj.infra.jpa.BaseCrudRepository;

import java.util.UUID;

public interface CustomerDomainRepository extends BaseCrudRepository<Customer, UUID> {

    boolean existsByIdentificationDocumentNumber(String number);

    @Override
    default Customer findByIdOrThrowNotFound(UUID id) {
        return findById(id).orElseThrow(CustomerNotFoundException::new);
    }

}
