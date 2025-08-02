package com.fiap.pj.core.servico.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

@Entity
@Table(name = "servicos")
@NoArgsConstructor
@Getter
public class Servico {

    @Id
    private UUID id;
    private String descricao;
    private BigDecimal preco;
    private String observacao;
    private boolean ativo;

    public Servico(UUID id, String descricao, BigDecimal preco, String observacao, boolean ativo) {
        this.id = requireNonNull(id);
        this.descricao = requireNonNull(descricao);
        this.preco = preco;
        this.observacao = observacao;
        this.ativo = ativo;
    }

    public void ativar() {
        this.ativo = true;
    }

    public void inativar() {
        this.ativo = false;
    }
}
