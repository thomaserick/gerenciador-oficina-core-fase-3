package com.fiap.pj.core.cliente.app;

import com.fiap.pj.core.cliente.domain.ClienteDomainRepository;
import com.fiap.pj.core.cliente.usecase.InativarClienteUserCase;
import com.fiap.pj.core.cliente.usecase.command.InativarClienteCommand;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class InativarClienteService implements InativarClienteUserCase {

    private final ClienteDomainRepository repository;

    @Override
    public void handle(InativarClienteCommand cmd) {
        var cliente = repository.findByIdOrThrowNotFound(cmd.id());
        cliente.inativar();
        repository.save(cliente);
    }
}
