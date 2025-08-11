package com.fiap.pj.core.ordemservico.domain;

import com.fiap.pj.core.ordemservico.domain.enums.OrdemServicoStatus;
import com.fiap.pj.core.ordemservico.exception.OrdemServicoExceptions.OrdemServicoStatusInvalidoAguardandoAprovacaoException;
import com.fiap.pj.core.ordemservico.exception.OrdemServicoExceptions.OrdemServicoStatusInvalidoAguardandoRetiradaException;
import com.fiap.pj.core.ordemservico.exception.OrdemServicoExceptions.OrdemServicoStatusInvalidoDiagnosticoException;
import com.fiap.pj.core.ordemservico.exception.OrdemServicoExceptions.OrdemServicoStatusInvalidoEmExecucaoException;
import com.fiap.pj.core.ordemservico.exception.OrdemServicoExceptions.OrdemServicoStatusInvalidoEntregueException;
import com.fiap.pj.core.ordemservico.exception.OrdemServicoExceptions.OrdemServicoStatusInvalidoFinalizadaException;
import com.fiap.pj.core.util.DateTimeUtils;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "ordem_servico")
@NoArgsConstructor
@Getter
public class OrdemServico {

    @Id
    private UUID id;
    private UUID clienteId;
    private UUID veiculoId;
    private UUID usuarioId;
    @Enumerated(EnumType.STRING)
    private OrdemServicoStatus status;
    private ZonedDateTime dataCriacao;
    private ZonedDateTime dataConclusao;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "diagnosticoId", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Diagnostico diagnostico;

    @Builder
    public OrdemServico(
            UUID id,
            UUID clienteId,
            UUID veiculoId,
            UUID usuarioId, OrdemServicoStatus status
    ) {
        this.id = id;
        this.clienteId = clienteId;
        this.veiculoId = veiculoId;
        this.usuarioId = usuarioId;
        this.status = status;
        this.dataCriacao = DateTimeUtils.getNow();
        this.dataConclusao = DateTimeUtils.getNow().plusDays(1);
    }

    public void moverEmDiagnostico() {
        if (!OrdemServicoStatus.CRIADA.equals(this.status)) {
            throw new OrdemServicoStatusInvalidoDiagnosticoException();
        }
        this.status = OrdemServicoStatus.EM_DIAGNOSTICO;
    }

    public void moverAguardandoAprovacao() {
        if (!OrdemServicoStatus.EM_DIAGNOSTICO.equals(this.status)) {
            throw new OrdemServicoStatusInvalidoAguardandoAprovacaoException();
        }
        this.status = OrdemServicoStatus.AGUARDANDO_APROVACAO;
    }

    public void moverEmExecucao() {
        if (OrdemServicoStatus.AGUARDANDO_APROVACAO.equals(this.status) ||
                OrdemServicoStatus.EM_DIAGNOSTICO.equals(this.status)) {
            this.status = OrdemServicoStatus.EM_EXECUCAO;
        } else {
            throw new OrdemServicoStatusInvalidoEmExecucaoException();
        }

    }

    public void moverFinalizada() {
        if (!OrdemServicoStatus.EM_EXECUCAO.equals(this.status)) {
            throw new OrdemServicoStatusInvalidoFinalizadaException();
        }
        this.status = OrdemServicoStatus.FINALIZADA;
    }

    public void moverAguardandoRetirada() {
        if (!OrdemServicoStatus.FINALIZADA.equals(this.status)) {
            throw new OrdemServicoStatusInvalidoAguardandoRetiradaException();
        }
        this.status = OrdemServicoStatus.AGUARDANDO_RETIRADA;
    }

    public void moverEntregue() {
        if (!OrdemServicoStatus.AGUARDANDO_RETIRADA.equals(this.status)) {
            throw new OrdemServicoStatusInvalidoEntregueException();
        }
        this.status = OrdemServicoStatus.ENTREGUE;
    }

    public void realizarDiagnostico(String descricao) {
        this.validarStatusParaInserirDiagnostico();
        Diagnostico diagnosticoCriado = new Diagnostico(this.id, descricao);
        this.diagnostico = diagnosticoCriado;
    }

    private void validarStatusParaInserirDiagnostico() {
        if (!OrdemServicoStatus.EM_DIAGNOSTICO.equals(this.status)) {
            throw new OrdemServicoStatusInvalidoDiagnosticoException();
        }
    }
}