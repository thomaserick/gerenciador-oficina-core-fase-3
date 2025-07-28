package com.fiap.pj.core.customer.app;

import com.fiap.pj.core.customer.domain.Customer;
import com.fiap.pj.core.customer.domain.CustomerDomainRepository;
import com.fiap.pj.core.customer.usecase.UpdateCustomerUserCase;
import com.fiap.pj.core.customer.usecase.command.UpdateCustomerCommand;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class UpdateCustomerService implements UpdateCustomerUserCase {

    private final CustomerDomainRepository repository;

    @Override
    public Customer handle(UpdateCustomerCommand cmd) {
        var customer = repository.findByIdOrThrowNotFound(cmd.getId());
        customer.update(cmd.getName(), cmd.getEmail(), cmd.getAddress(), cmd.getPhone());
        return repository.save(customer);
    }
}
