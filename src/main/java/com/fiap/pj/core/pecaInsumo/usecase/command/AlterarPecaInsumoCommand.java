package com.fiap.pj.core.pecaInsumo.usecase.command;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class AlterarPecaInsumoCommand extends PecaInsumoCommand {

    private UUID id;

    public AlterarPecaInsumoCommand(String nome, String descricao, BigDecimal valorUnitario, Integer quantidadeEstoque) {
        super(nome, descricao, valorUnitario, quantidadeEstoque);
    }
} 