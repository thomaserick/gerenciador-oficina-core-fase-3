package com.fiap.pj.core.veiculo.util.factory;

import com.fiap.pj.core.veiculo.domain.Veiculo;
import com.fiap.pj.core.veiculo.usecase.command.AdicionarVeiculoClienteCommand;

import java.util.UUID;

public class VeiculoTestFactory {

    public static final UUID ID = UUID.randomUUID();
    public static final String PLACA = "MOI-0037";
    public static final String MARCA = "FIAT";
    public static final String MODELO = "FIAT 147";
    public static final int ANO = 2025;

    public static Veiculo umVeiculo(UUID clienteId) {
        return new Veiculo(ID, PLACA, MODELO, MARCA, ANO, clienteId);
    }

    public static AdicionarVeiculoClienteCommand umAdicionarVeiculoClienteCommand(UUID clienteId) {
        return new AdicionarVeiculoClienteCommand(clienteId, PLACA, MARCA, MODELO, ANO);
    }
}
