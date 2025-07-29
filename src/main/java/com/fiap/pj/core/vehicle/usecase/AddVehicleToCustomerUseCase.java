package com.fiap.pj.core.vehicle.usecase;

import com.fiap.pj.core.vehicle.usecase.command.AddVehicleToCustomerCommand;

public interface AddVehicleToCustomerUseCase {

    void handle(AddVehicleToCustomerCommand cmd);
}
