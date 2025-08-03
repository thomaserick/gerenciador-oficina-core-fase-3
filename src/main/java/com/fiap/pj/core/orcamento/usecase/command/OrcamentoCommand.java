package com.fiap.pj.core.orcamento.usecase.command;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;
import java.util.UUID;

@Getter
@AllArgsConstructor
public abstract class OrcamentoCommand {

    protected String descricao;
    @NotNull(message = "Identificador do cliente não pode ser nulo")
    protected UUID clienteId;
    @NotNull(message = "Identificador do veiculo não pode ser nulo")
    protected UUID veiculoId;
    @PositiveOrZero(message = "Hodômetro do veiculo não pode ser negativa.")
    protected int hodometro;

    @Valid
    protected Set<OrcamentoItemServicoCommand> servicos;


}
