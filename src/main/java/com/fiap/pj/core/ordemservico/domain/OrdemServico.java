package com.fiap.pj.core.ordemservico.domain;

import com.fiap.pj.core.ordemservico.domain.enums.OrdemServicoStatus;
import com.fiap.pj.core.ordemservico.exception.OrdemServicoExceptions.OrdemServicoStatusInvalidoDianosticoException;
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
            UUID clienteId,
            UUID veiculoId,
            UUID usuarioId
    ) {
        this.id = UUID.randomUUID();
        this.clienteId = clienteId;
        this.veiculoId = veiculoId;
        this.usuarioId = usuarioId;
        this.status = OrdemServicoStatus.CRIADA;
        this.dataCriacao = DateTimeUtils.getNow();
        this.dataConclusao = DateTimeUtils.getNow().plusDays(1);
    }

    public void realizarDiagnostico(String descricao) {
        this.validarStatusParaInserirDiagnostico();
        Diagnostico diagnosticoCriado = new Diagnostico(this.id, descricao);
        this.diagnostico = diagnosticoCriado;
    }

    private void validarStatusParaInserirDiagnostico() {
        if (!OrdemServicoStatus.EM_DIAGNOSTICO.equals(this.status)) {
            throw new OrdemServicoStatusInvalidoDianosticoException();
        }
    }
}