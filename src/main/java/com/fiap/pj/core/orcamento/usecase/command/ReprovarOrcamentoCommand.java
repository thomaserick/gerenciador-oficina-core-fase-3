package com.fiap.pj.core.orcamento.usecase.command;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ReprovarOrcamentoCommand(
        @NotNull(message = "Identificador do orcamento não pode ser nulo.")
        UUID id) {
}
