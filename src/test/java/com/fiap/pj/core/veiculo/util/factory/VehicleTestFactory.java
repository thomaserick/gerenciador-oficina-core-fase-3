package com.fiap.pj.core.veiculo.util.factory;

import com.fiap.pj.core.veiculo.domain.Veiculo;
import com.fiap.pj.core.veiculo.usecase.command.AdicionarVeiculoClienteCommand;

import java.util.UUID;

public class VehicleTestFactory {

    public static final UUID ID = UUID.randomUUID();
    public static final String PLATE = "MOI-0037";
    public static final String MAKE = "FIAT";
    public static final String MODEL = "FIAT 147";
    public static final int YEAR = 2025;

    public static Veiculo oneVehicle(UUID clienteId) {
        return new Veiculo(ID, PLATE, MODEL, MAKE, YEAR, clienteId);
    }

    public static AdicionarVeiculoClienteCommand oneAddVehicleToCustomerCommand(UUID clienteId) {
        return new AdicionarVeiculoClienteCommand(clienteId, PLATE, MAKE, MODEL, YEAR);
    }
}
