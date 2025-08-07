package com.fiap.pj.core.orcamento.usecase.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.UUID;

public record OrcamentoItemPecaInsumoCommand(
        @NotNull(message = "Identificador da peça/insumo não pode ser nulo")
        UUID pecaInsumoId,
        @PositiveOrZero(message = "Quantidade não pode ser negativa")
        BigDecimal quantidade
) {
}
