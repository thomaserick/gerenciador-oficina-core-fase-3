package com.fiap.pj.core.cliente.app;

import com.fiap.pj.core.cliente.domain.Cliente;
import com.fiap.pj.core.cliente.domain.ClienteDomainRepository;
import com.fiap.pj.core.cliente.usecase.AlterarClienteUserCase;
import com.fiap.pj.core.cliente.usecase.command.AlterarClienteCommand;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class AlterarClienteService implements AlterarClienteUserCase {

    private final ClienteDomainRepository repository;

    @Override
    public Cliente handle(AlterarClienteCommand cmd) {
        var cliente = repository.findByIdOrThrowNotFound(cmd.getId());
        cliente.alterar(cmd.getNome(), cmd.getEmail(), cmd.getEndereco(), cmd.getTelefone());
        return repository.save(cliente);
    }
}
