package com.fiap.pj.core.orcamento.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

import static java.util.Objects.requireNonNull;


@Entity
@Table(name = "orcamentos_servicos")
@NoArgsConstructor
@Getter
public class OrcamentoItemServico {

    @Id
    private UUID id;
    private UUID servicoId;
    private UUID orcamentoId;
    private String descricao;
    private BigDecimal preco;
    private Integer quantidade;

    @Builder
    public OrcamentoItemServico(UUID id, UUID servicoId, UUID orcamentoId, String descricao, BigDecimal preco, Integer quantidade) {
        this.id = requireNonNull(id);
        this.servicoId = requireNonNull(servicoId);
        this.orcamentoId = requireNonNull(orcamentoId);
        this.descricao = requireNonNull(descricao);
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public BigDecimal valorTotal() {
        return this.getPreco().multiply(new BigDecimal(this.getQuantidade()));
    }
}
