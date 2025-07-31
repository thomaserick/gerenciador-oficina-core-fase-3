package com.fiap.pj.core.customer.domain;


import com.fiap.pj.infra.jpa.BaseCrudRepository;

import java.util.UUID;

public interface CustomerDomainRepository extends BaseCrudRepository<Customer, UUID> {

    boolean existsByIdentificationDocumentNumber(String number);

}
