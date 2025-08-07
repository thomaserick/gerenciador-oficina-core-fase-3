package com.fiap.pj.core.veiculo.usecase;

import com.fiap.pj.core.veiculo.usecase.command.RemoverVeiculoClienteCommand;

public interface RemoverVeiculoClienteUseCase {
    void handle(RemoverVeiculoClienteCommand cmd);
} 