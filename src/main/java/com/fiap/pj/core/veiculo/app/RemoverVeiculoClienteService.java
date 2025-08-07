package com.fiap.pj.core.veiculo.app;

import com.fiap.pj.core.cliente.domain.ClienteDomainRepository;
import com.fiap.pj.core.veiculo.domain.VeiculoDomainRepository;
import com.fiap.pj.core.veiculo.usecase.RemoverVeiculoClienteUseCase;
import com.fiap.pj.core.veiculo.usecase.command.RemoverVeiculoClienteCommand;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class RemoverVeiculoClienteService implements RemoverVeiculoClienteUseCase {

    private final ClienteDomainRepository clienteDomainRepository;
    private final VeiculoDomainRepository veiculoDomainRepository;

    @Override
    public void handle(RemoverVeiculoClienteCommand cmd) {
        var cliente = clienteDomainRepository.findByIdOrThrowNotFound(cmd.clienteId());
        var veiculo = veiculoDomainRepository.findByIdOrThrowNotFound(cmd.veiculoId());

        cliente.validarVeiculo(veiculo.getId());
        cliente.getVeiculos().remove(veiculo);

        clienteDomainRepository.save(cliente);
        veiculoDomainRepository.delete(veiculo);
    }
} 