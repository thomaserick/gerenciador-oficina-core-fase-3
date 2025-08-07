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
@Table(name = "orcamentos_pecas_insumos")
@NoArgsConstructor
@Getter
public class OrcamentoItemPecaInsumo {

    @Id
    private UUID id;
    private UUID pecasInsumosId;
    private UUID orcamentoId;
    private String descricao;
    private BigDecimal preco;
    private BigDecimal quantidade;

    @Builder
    public OrcamentoItemPecaInsumo(UUID id, UUID pecasInsumosId, UUID orcamentoId, String descricao, BigDecimal preco, BigDecimal quantidade) {
        this.id = requireNonNull(id);
        this.pecasInsumosId = requireNonNull(pecasInsumosId);
        this.orcamentoId = requireNonNull(orcamentoId);
        this.descricao = requireNonNull(descricao);
        this.preco = requireNonNull(preco);
        this.quantidade = requireNonNull(quantidade);
    }

    public BigDecimal valorTotal() {
        return this.getPreco().multiply(this.getQuantidade());
    }
}
