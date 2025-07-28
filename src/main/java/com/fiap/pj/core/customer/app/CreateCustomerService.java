package com.fiap.pj.core.customer.app;

import com.fiap.pj.core.customer.domain.Customer;
import com.fiap.pj.core.customer.domain.CustomerDomainRepository;
import com.fiap.pj.core.customer.exception.CustomerExceptions.DocumentIdentificationDuplicateException;
import com.fiap.pj.core.customer.usecase.CreateCustomerUserCase;
import com.fiap.pj.core.customer.usecase.command.CreateCustomerCommand;
import com.fiap.pj.core.sk.documentoidentificacao.domain.IdentificationDocument;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class CreateCustomerService implements CreateCustomerUserCase {

    private final CustomerDomainRepository repository;


    @Override
    public Customer handle(CreateCustomerCommand cmd) {

        if (repository.existsByIdentificationDocumentNumber(cmd.getIdentificationDocument())) {
            throw new DocumentIdentificationDuplicateException();
        }

        var identificationDocument = IdentificationDocument.build(
                cmd.getIdentificationDocument());

        var customer = Customer.builder()
                .id(UUID.randomUUID())
                .name(cmd.getName())
                .email(cmd.getEmail())
                .active(true)
                .phone(cmd.getPhone())
                .address(cmd.getAddress())
                .identificationDocument(identificationDocument)
                .build();

        return repository.save(customer);
    }
}
