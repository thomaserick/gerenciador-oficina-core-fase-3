package com.fiap.pj.core.service.app;


import com.fiap.pj.core.service.domain.ServiceDomainRepository;
import com.fiap.pj.core.service.domain.ServiceEntity;
import com.fiap.pj.core.service.usecase.CreateServiceUseCase;
import com.fiap.pj.core.service.usecase.command.CreateServiceCommand;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class CreateServiceService implements CreateServiceUseCase {

    private final ServiceDomainRepository repository;

    @Override
    public ServiceEntity handle(CreateServiceCommand cmd) {
        var service = new ServiceEntity(UUID.randomUUID(), cmd.getDescription(), cmd.getPrice(), cmd.getObservation(), true);
        return repository.save(service);
    }
}
