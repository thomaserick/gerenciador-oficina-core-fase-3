package com.fiap.pj.core.orcamento.usecase.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.UUID;

public record OrcamentoItemServicoCommand(
        @NotNull(message = "Identificador do servico não pode ser nulo")
        UUID servicoId,
        @PositiveOrZero(message = "Quantidade não pode ser negativa")
        BigDecimal quantidade
) {
}
