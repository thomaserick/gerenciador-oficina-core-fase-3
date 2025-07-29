package com.fiap.pj.core.customer.app;

import com.fiap.pj.core.customer.domain.CustomerDomainRepository;
import com.fiap.pj.core.customer.usecase.DeactivateCustomerUserCase;
import com.fiap.pj.core.customer.usecase.command.DeactivateCustomerCommand;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class DeactivateCustomerService implements DeactivateCustomerUserCase {

    private final CustomerDomainRepository repository;

    @Override
    public void handle(DeactivateCustomerCommand cmd) {
        var customer = repository.findByIdOrThrowNotFound(cmd.id());
        customer.deactivate();
        repository.save(customer);
    }
}
