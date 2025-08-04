package com.fiap.pj.core.pecaInsumo.usecase.command;

public class CriarPecaInsumoCommand extends PecaInsumoCommand {

    public CriarPecaInsumoCommand(String nome, String descricao, java.math.BigDecimal valorUnitario, Integer quantidadeEstoque) {
        super(nome, descricao, valorUnitario, quantidadeEstoque);
    }
} 