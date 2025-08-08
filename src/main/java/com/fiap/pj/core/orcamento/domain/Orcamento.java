package com.fiap.pj.core.orcamento.domain;

import com.fiap.pj.core.orcamento.domain.enums.OrcamentoStatus;
import com.fiap.pj.core.orcamento.exception.OrcamentoExceptions.AlterarOrcamentoStatusInvalidoException;
import com.fiap.pj.core.orcamento.exception.OrcamentoExceptions.ReprovarOrcamentoStatusInvalidoException;
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
    private UUID usuarioId;
    private int hodometro;
    @Enumerated(EnumType.STRING)
    private OrcamentoStatus status;
    private UUID ordemServicoId;
    private ZonedDateTime dataCriacao;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "orcamentoId")
    private Set<OrcamentoItemServico> servicos = new HashSet<>();

    @Builder
    public Orcamento(UUID id, String descricao, UUID clienteId, UUID veiculoId, UUID usuarioId, int hodometro, OrcamentoStatus status, UUID ordemServicoId) {
        this.id = requireNonNull(id);
        this.descricao = descricao;
        this.clienteId = requireNonNull(clienteId);
        this.veiculoId = requireNonNull(veiculoId);
        this.usuarioId = requireNonNull(usuarioId);
        this.hodometro = hodometro;
        this.status = requireNonNull(status);
        this.ordemServicoId = ordemServicoId;
        this.dataCriacao = DateTimeUtils.getNow();
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "orcamentoId")
    private Set<OrcamentoItemPecaInsumo> pecasInsumos = new HashSet<>();

    public void alterar(String descricao, UUID clienteId, UUID veiculoId, int hodometro) {
        if (!this.status.isAguardandoAprovacao()) {
            throw new AlterarOrcamentoStatusInvalidoException();
        }
        this.descricao = descricao;
        this.clienteId = requireNonNull(clienteId);
        this.veiculoId = requireNonNull(veiculoId);
        this.hodometro = hodometro;
    }

    public void adicionarServico(OrcamentoItemServico servico) {
        this.servicos.add(servico);
    }

    public void adicionaPecaInsumo(OrcamentoItemPecaInsumo pecaInsumo) {
        this.pecasInsumos.add(pecaInsumo);
    }

    public void aprovar() {
        if (!this.status.isAguardandoAprovacao()) {
            throw new AlterarOrcamentoStatusInvalidoException();
        }
        this.status = OrcamentoStatus.APROVADO;
    }

    public void reprovar() {
        if (!this.status.isAguardandoAprovacao()) {
            throw new ReprovarOrcamentoStatusInvalidoException();
        }
        this.status = OrcamentoStatus.REPROVADO;
    }

    public void vincularOrdemServico(UUID ordemServicoId) {
        this.ordemServicoId = requireNonNull(ordemServicoId);
    }

    public BigDecimal getValorTotal() {
        var totalPecasInsumo = this.getPecasInsumos().stream().map(OrcamentoItemPecaInsumo::valorTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
        return this.getServicos().stream().map(OrcamentoItemServico::valorTotal).reduce(BigDecimal.ZERO, BigDecimal::add).add(totalPecasInsumo);
    }
}
