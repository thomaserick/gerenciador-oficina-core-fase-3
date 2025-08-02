package com.fiap.pj.core.veiculo.app;


import com.fiap.pj.core.cliente.domain.ClienteDomainRepository;
import com.fiap.pj.core.veiculo.domain.Veiculo;
import com.fiap.pj.core.veiculo.domain.VeiculoDomainRepository;
import com.fiap.pj.core.veiculo.exception.VeiculoExceptions.VeiucloPlacaDuplicadaException;
import com.fiap.pj.core.veiculo.usecase.AdicionarVeiculoClienteUseCase;
import com.fiap.pj.core.veiculo.usecase.command.AdicionarVeiculoClienteCommand;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class AdicionarVeiculoClienteService implements AdicionarVeiculoClienteUseCase {

    private final ClienteDomainRepository clienteDomainRepository;
    private final VeiculoDomainRepository repository;

    @Override
    public void handle(AdicionarVeiculoClienteCommand cmd) {

        if (repository.existsByPlaca(cmd.placa())) {
            throw new VeiucloPlacaDuplicadaException();
        }

        var cliente = clienteDomainRepository.findByIdOrThrowNotFound(cmd.clienteId());

        var veiculo = Veiculo.builder().id(UUID.randomUUID())
                .placa(cmd.placa())
                .marca(cmd.marca())
                .modelo(cmd.modelo())
                .ano(cmd.ano())
                .clienteId(cliente.getId())
                .build();

        cliente.adicionarVeiculo(veiculo);

        clienteDomainRepository.save(cliente);

    }
}
