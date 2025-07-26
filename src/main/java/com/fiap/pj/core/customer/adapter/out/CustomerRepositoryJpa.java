package com.fiap.pj.core.customer.adapter.out;


import com.fiap.pj.core.customer.domain.Customer;
import com.fiap.pj.core.customer.domain.CustomerDomainRepository;
import org.springframework.data.repository.Repository;

import java.util.UUID;


public interface CustomerRepositoryJpa extends CustomerDomainRepository, Repository<Customer, UUID> {
}