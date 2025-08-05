package com.fiap.pj.core.pecaInsumo.usecase.command;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class PecaInsumoCommand {

    private final String nome;
    private final String descricao;
    private final BigDecimal valorUnitario;
    private final Integer quantidadeEstoque;
    private final Integer quantidadeMinimoEstoque;

    public PecaInsumoCommand(String nome, String descricao, BigDecimal valorUnitario, Integer quantidadeEstoque, Integer quantidadeMinimoEstoque) {
        this.nome = nome;
        this.descricao = descricao;
        this.valorUnitario = valorUnitario;
        this.quantidadeEstoque = quantidadeEstoque;
        this.quantidadeMinimoEstoque = quantidadeMinimoEstoque;
    }
} 