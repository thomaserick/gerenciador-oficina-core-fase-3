package com.fiap.pj.core.servico.app.usecase.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public abstract class ServicoCommand {

    @NotBlank(message = "Descricao do servico não pode estar vazio.")
    protected String descricao;
    protected BigDecimal preco;
    protected String observacao;

    protected ServicoCommand(String descricao, BigDecimal preco, String observacao) {
        this.descricao = descricao;
        this.preco = preco;
        this.observacao = observacao;
    }
}
