package com.fiap.pj.core.customer.app;

import com.fiap.pj.core.customer.domain.CustomerDomainRepository;
import com.fiap.pj.core.customer.usecase.ActivateCustomerUserCase;
import com.fiap.pj.core.customer.usecase.command.ActivateCustomerCommand;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class ActivateCustomerService implements ActivateCustomerUserCase {

    private final CustomerDomainRepository repository;

    @Override
    public void handle(ActivateCustomerCommand cmd) {
        var customer = repository.findByIdOrThrowNotFound(cmd.id());
        customer.activate();
        repository.save(customer);
    }
}
