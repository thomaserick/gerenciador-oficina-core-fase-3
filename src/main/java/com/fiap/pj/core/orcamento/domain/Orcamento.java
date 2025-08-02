package com.fiap.pj.core.orcamento.domain;

import com.fiap.pj.core.orcamento.domain.enums.OrcamentoStatus;
import com.fiap.pj.core.util.DateTimeUtils;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

@Entity
@Table(name = "orcamentos")
@NoArgsConstructor
@Getter
public class Orcamento {

    @Id
    private UUID id;
    private String descricao;
    private UUID clienteId;
    private UUID veiculoId;
    private int hodometer;
    @Enumerated(EnumType.STRING)
    private OrcamentoStatus status;
    private UUID ordemServicoId;
    private ZonedDateTime dataCriacao;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "orcamentoId")
    private Set<OrcamentoItemServico> servicos = new HashSet<>();

    @Builder
    public Orcamento(UUID id, String descricao, UUID clienteId, UUID veiculoId, int hodometer, OrcamentoStatus status, UUID ordemServicoId) {
        this.id = requireNonNull(id);
        this.descricao = descricao;
        this.clienteId = requireNonNull(clienteId);
        this.veiculoId = requireNonNull(veiculoId);
        this.hodometer = hodometer;
        this.status = requireNonNull(status);
        this.ordemServicoId = ordemServicoId;
        this.dataCriacao = DateTimeUtils.getNow();
    }

    public void adicionarServicos(OrcamentoItemServico servico) {
        this.servicos.add(servico);
    }

    public BigDecimal getValorTotal() {
        return this.getServicos().stream().map(OrcamentoItemServico::valorTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}
