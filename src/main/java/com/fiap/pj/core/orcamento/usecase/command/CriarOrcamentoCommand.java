package com.fiap.pj.core.orcamento.usecase.command;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;

import java.util.Set;
import java.util.UUID;

@Getter
public class CriarOrcamentoCommand {

    private String descricao;
    @NotNull(message = "Identificador do cliente não pode ser nulo")
    private UUID clienteId;
    @NotNull(message = "Identificador do veiculo não pode ser nulo")
    private UUID veiculoId;
    @PositiveOrZero(message = "Hodômetro do veiculo não pode ser negativa.")
    private int hodometer;

    @Valid
    private Set<OrcamentoItemServicoCommand> servicos;
}
