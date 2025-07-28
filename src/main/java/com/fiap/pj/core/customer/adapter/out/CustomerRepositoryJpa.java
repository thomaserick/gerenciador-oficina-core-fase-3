package com.fiap.pj.core.customer.adapter.out;


import com.fiap.pj.core.customer.domain.Customer;
import com.fiap.pj.core.customer.domain.CustomerDomainRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;


public interface CustomerRepositoryJpa extends CustomerDomainRepository, Repository<Customer, UUID> {

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM Customer c WHERE c.identificationDocument.number = :number")
    boolean existsByIdentificationDocumentNumber(@Param("number") String number);
}