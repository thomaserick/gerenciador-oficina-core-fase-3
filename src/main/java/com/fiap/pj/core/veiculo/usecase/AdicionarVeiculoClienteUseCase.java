package com.fiap.pj.core.veiculo.usecase;

import com.fiap.pj.core.veiculo.domain.Veiculo;
import com.fiap.pj.core.veiculo.usecase.command.AdicionarVeiculoClienteCommand;

public interface AdicionarVeiculoClienteUseCase {

    Veiculo handle(AdicionarVeiculoClienteCommand cmd);
}
