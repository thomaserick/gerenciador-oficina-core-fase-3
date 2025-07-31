package com.fiap.pj.core.estimate.usecase.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.UUID;

public record EstimateServiceItemCommand(
        @NotNull(message = "Identificado do servico não pode ser nulo")
        UUID serviceId,
        @PositiveOrZero(message = "Quantidade não pode ser negativa")
        BigDecimal quantity
) {
}
