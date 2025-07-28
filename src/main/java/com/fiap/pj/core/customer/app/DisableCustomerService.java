package com.fiap.pj.core.customer.app;

import com.fiap.pj.core.customer.domain.CustomerDomainRepository;
import com.fiap.pj.core.customer.usecase.DisableCustomerUserCase;
import com.fiap.pj.core.customer.usecase.command.DisableCustomerCommand;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class DisableCustomerService implements DisableCustomerUserCase {

    private final CustomerDomainRepository repository;

    @Override
    public void handle(DisableCustomerCommand cmd) {
        var customer = repository.findByIdOrThrowNotFound(cmd.id());
        customer.disable();
        repository.save(customer);
    }
}
