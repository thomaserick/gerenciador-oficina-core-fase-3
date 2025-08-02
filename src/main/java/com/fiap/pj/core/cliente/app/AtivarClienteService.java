package com.fiap.pj.core.cliente.app;

import com.fiap.pj.core.cliente.domain.ClienteDomainRepository;
import com.fiap.pj.core.cliente.usecase.AtivarClienteUserCase;
import com.fiap.pj.core.cliente.usecase.command.AtivarClienteCommand;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class AtivarClienteService implements AtivarClienteUserCase {

    private final ClienteDomainRepository repository;

    @Override
    public void handle(AtivarClienteCommand cmd) {
        var cliente = repository.findByIdOrThrowNotFound(cmd.id());
        cliente.ativar();
        repository.save(cliente);
    }
}
