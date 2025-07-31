package com.fiap.pj.core.estimate.usecase.command;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;

import java.util.Set;
import java.util.UUID;

@Getter
public class CreateEstimateCommand {

    private String description;
    @NotNull(message = "Identificador do cliente não pode ser nulo")
    private UUID customerId;
    @NotNull(message = "Identificador do veiculo não pode ser nulo")
    private UUID vehicleId;
    @PositiveOrZero(message = "Odômetro do veiculo não pode ser negativa.")
    private int odometer;

    @Valid
    private Set<EstimateServiceItemCommand> services;
}
