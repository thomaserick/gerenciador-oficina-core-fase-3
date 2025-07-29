package com.fiap.pj.core.vehicle.util.factory;

import com.fiap.pj.core.vehicle.domain.Vehicle;
import com.fiap.pj.core.vehicle.usecase.command.AddVehicleToCustomerCommand;

import java.util.UUID;

public class VehicleTestFactory {

    public static final UUID ID = UUID.randomUUID();
    public static final String PLATE = "MOI-0037";
    public static final String MAKE = "FIAT";
    public static final String MODEL = "FIAT 147";
    public static final int YEAR = 2025;

    public static Vehicle oneVehicle(UUID customerId) {
        return new Vehicle(ID, PLATE, MODEL, MAKE, YEAR, customerId);
    }

    public static AddVehicleToCustomerCommand oneAddVehicleToCustomerCommand(UUID customerId) {
        return new AddVehicleToCustomerCommand(customerId, PLATE, MAKE, MODEL, YEAR);
    }
}
