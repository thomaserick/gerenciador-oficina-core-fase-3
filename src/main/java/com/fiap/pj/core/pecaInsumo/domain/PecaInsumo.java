package com.fiap.pj.core.pecaInsumo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

@Entity
@Table(name = "pecas_insumos")
@NoArgsConstructor
@Getter
public class PecaInsumo {

    @Id
    private UUID id;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private String descricao;
    
    @Column(nullable = false)
    private BigDecimal valorUnitario;
    
    @Column(nullable = false)
    private Integer quantidadeEstoque;
    
    @Column(nullable = false)
    private Integer quantidadeMinimoEstoque;

    public PecaInsumo(UUID id, String nome, String descricao, BigDecimal valorUnitario, Integer quantidadeEstoque, Integer quantidadeMinimoEstoque) {
        this.id = requireNonNull(id);
        this.nome = requireNonNull(nome);
        this.descricao = requireNonNull(descricao);
        this.valorUnitario = requireNonNull(valorUnitario);
        this.quantidadeEstoque = requireNonNull(quantidadeEstoque);
        this.quantidadeMinimoEstoque = requireNonNull(quantidadeMinimoEstoque);
    }

    public void adicionarEstoque(Integer quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }
        this.quantidadeEstoque += quantidade;
    }

    public void removerEstoque(Integer quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }
        if (this.quantidadeEstoque < quantidade) {
            throw new IllegalArgumentException("Quantidade em estoque insuficiente");
        }
        this.quantidadeEstoque -= quantidade;
    }

    public void atualizarDados(String nome, String descricao, BigDecimal valorUnitario, Integer quantidadeMinimoEstoque) {
        this.nome = requireNonNull(nome);
        this.descricao = requireNonNull(descricao);
        this.valorUnitario = requireNonNull(valorUnitario);
        this.quantidadeMinimoEstoque = requireNonNull(quantidadeMinimoEstoque);
    }
    
    public boolean estoqueBaixo() {
        return this.quantidadeEstoque <= this.quantidadeMinimoEstoque;
    }
} 