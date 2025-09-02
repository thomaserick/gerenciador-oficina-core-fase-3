package com.fiap.pj.core.veiculo.app;

import com.fiap.pj.core.cliente.app.gateways.ClienteGateway;
import com.fiap.pj.core.cliente.exception.ClienteExceptions.ClienteNaoEncontradoException;
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

    private final ClienteGateway clienteGateway;
    private final VeiculoDomainRepository veiculoDomainRepository;

    @Override
    public void handle(RemoverVeiculoClienteCommand cmd) {
        var cliente = clienteGateway.buscarPorId(cmd.clienteId()).orElseThrow(ClienteNaoEncontradoException::new);
        var veiculo = veiculoDomainRepository.findByIdOrThrowNotFound(cmd.veiculoId());

        cliente.validarVeiculo(veiculo.getId());
        cliente.getVeiculos().remove(veiculo);

        clienteGateway.alterar(cliente);
        veiculoDomainRepository.delete(veiculo);
    }
} 