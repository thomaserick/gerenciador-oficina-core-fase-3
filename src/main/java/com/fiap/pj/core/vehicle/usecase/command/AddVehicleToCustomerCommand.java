package com.fiap.pj.core.vehicle.usecase.command;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public record AddVehicleToCustomerCommand(@JsonIgnore UUID customerId, String plate, String make, String model,
                                          String year) {

    public AddVehicleToCustomerCommand withCustomerId(UUID customarId) {
        return new AddVehicleToCustomerCommand(customarId, this.plate, this.make, this.model, this.year);
    }
}
