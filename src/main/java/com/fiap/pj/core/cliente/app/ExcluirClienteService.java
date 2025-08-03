package com.fiap.pj.core.cliente.app;

import com.fiap.pj.core.cliente.domain.ClienteDomainRepository;
import com.fiap.pj.core.cliente.usecase.ExcluirClienteUserCase;
import com.fiap.pj.core.cliente.usecase.command.ExcluirClienteCommand;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class ExcluirClienteService implements ExcluirClienteUserCase {

    private final ClienteDomainRepository repository;

    @Override
    public void handle(ExcluirClienteCommand cmd) {
        var cliente = repository.findByIdOrThrowNotFound(cmd.id());
        repository.delete(cliente);
    }
}
