package com.fiap.pj.core.ordemservico.domain;

import com.fiap.pj.core.cliente.domain.Cliente;
import com.fiap.pj.core.ordemservico.domain.enums.OrdemServicoStatus;
import com.fiap.pj.core.ordemservico.exception.OrdemServicoExceptions.OrdemServicoStatusInvalidoAguardandoAprovacaoException;
import com.fiap.pj.core.ordemservico.exception.OrdemServicoExceptions.OrdemServicoStatusInvalidoAguardandoRetiradaException;
import com.fiap.pj.core.ordemservico.exception.OrdemServicoExceptions.OrdemServicoStatusInvalidoDiagnosticoException;
import com.fiap.pj.core.ordemservico.exception.OrdemServicoExceptions.OrdemServicoStatusInvalidoEmExecucaoException;
import com.fiap.pj.core.ordemservico.exception.OrdemServicoExceptions.OrdemServicoStatusInvalidoEntregueException;
import com.fiap.pj.core.ordemservico.exception.OrdemServicoExceptions.OrdemServicoStatusInvalidoFinalizadaException;
import com.fiap.pj.core.usuario.domain.Usuario;
import com.fiap.pj.core.util.DateTimeUtils;
import com.fiap.pj.core.veiculo.domain.Veiculo;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static com.fiap.pj.core.ordemservico.domain.enums.OrdemServicoStatus.AGUARDANDO_APROVACAO;
import static com.fiap.pj.core.ordemservico.domain.enums.OrdemServicoStatus.EM_DIAGNOSTICO;
import static com.fiap.pj.core.ordemservico.domain.enums.OrdemServicoStatus.EM_EXECUCAO;
import static com.fiap.pj.core.ordemservico.domain.enums.OrdemServicoStatus.FINALIZADA;

@Entity
@Table(name = "ordens_servico")
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

    @OneToOne
    @JoinColumn(name = "clienteId", referencedColumnName = "id", insertable = false, updatable = false)
    private Cliente cliente;

    @OneToOne
    @JoinColumn(name = "veiculoId", referencedColumnName = "id", insertable = false, updatable = false)
    private Veiculo veiculo;

    @OneToOne
    @JoinColumn(name = "usuarioId", referencedColumnName = "id", insertable = false, updatable = false)
    private Usuario usuario;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "diagnosticoId", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Diagnostico diagnostico;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ordemServicoId")
    @OrderBy("dataCriacao DESC")
    private Set<SituacaoOrdemServico> historicoSituacao = new HashSet<>();

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
        novaSituacao(EM_DIAGNOSTICO);
    }

    public void moverAguardandoAprovacao() {
        if (!OrdemServicoStatus.EM_DIAGNOSTICO.equals(this.status)) {
            throw new OrdemServicoStatusInvalidoAguardandoAprovacaoException();
        }
        novaSituacao(AGUARDANDO_APROVACAO);
    }

    public void moverEmExecucao() {
        if (OrdemServicoStatus.AGUARDANDO_APROVACAO.equals(this.status) ||
                OrdemServicoStatus.EM_DIAGNOSTICO.equals(this.status)) {
            novaSituacao(EM_EXECUCAO);
        } else {
            throw new OrdemServicoStatusInvalidoEmExecucaoException();
        }

    }

    public void moverFinalizada() {
        if (!OrdemServicoStatus.EM_EXECUCAO.equals(this.status)) {
            throw new OrdemServicoStatusInvalidoFinalizadaException();
        }
        novaSituacao(FINALIZADA);
    }

    public void moverAguardandoRetirada() {
        if (!FINALIZADA.equals(this.status)) {
            throw new OrdemServicoStatusInvalidoAguardandoRetiradaException();
        }

        novaSituacao(OrdemServicoStatus.AGUARDANDO_RETIRADA);
    }

    public void moverEntregue() {
        if (!OrdemServicoStatus.AGUARDANDO_RETIRADA.equals(this.status)) {
            throw new OrdemServicoStatusInvalidoEntregueException();
        }

        novaSituacao(OrdemServicoStatus.ENTREGUE);
    }

    public void realizarDiagnostico(String descricao) {
        this.validarStatusParaInserirDiagnostico();
        this.diagnostico = new Diagnostico(this.id, descricao);
    }

    private void validarStatusParaInserirDiagnostico() {
        if (!OrdemServicoStatus.EM_DIAGNOSTICO.equals(this.status)) {
            throw new OrdemServicoStatusInvalidoDiagnosticoException();
        }
    }

    private void novaSituacao(OrdemServicoStatus status) {
        var situacao = new SituacaoOrdemServico(status, this.getId());
        this.getHistoricoSituacao().add(situacao);
        this.status = status;
    }
}