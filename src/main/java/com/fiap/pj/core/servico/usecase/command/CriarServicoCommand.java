package com.fiap.pj.core.servico.usecase.command;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CriarServicoCommand extends ServicoCommand {

    public CriarServicoCommand(String descricao, BigDecimal preco, String observacao) {
        super(descricao, preco, observacao);
    }
}
