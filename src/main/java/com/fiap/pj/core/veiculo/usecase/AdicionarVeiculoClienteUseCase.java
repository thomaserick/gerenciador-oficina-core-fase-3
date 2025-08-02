package com.fiap.pj.core.veiculo.usecase;

import com.fiap.pj.core.veiculo.usecase.command.AdicionarVeiculoClienteCommand;

public interface AdicionarVeiculoClienteUseCase {

    void handle(AdicionarVeiculoClienteCommand cmd);
}
