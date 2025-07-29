package com.fiap.pj.core.vehicle.usecase.command;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiap.pj.infra.validator.Plate;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AddVehicleToCustomerCommand(@JsonIgnore UUID customerId,
                                          @Plate
                                          String plate,
                                          String make,
                                          String model,
                                          @NotNull(message = "O ano não pode ser nulo")
                                          @Min(value = 1900, message = "O ano deve ser maior ou igual a 1900")
                                          @Max(value = 2100, message = "O ano deve ser menor ou igual a 2100")
                                          int year) {

    public AddVehicleToCustomerCommand withCustomerId(UUID customarId) {
        return new AddVehicleToCustomerCommand(customarId, this.plate, this.make, this.model, this.year);
    }
}
