package com.fiap.pj.ordemservico.domain;

import com.fiap.pj.core.util.DateTimeUtils;
import com.fiap.pj.ordemservico.domain.enums.OrdemServicoStatus;
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
    @Enumerated(EnumType.STRING)
    private OrdemServicoStatus status;
    private ZonedDateTime dataCriacao;
    private ZonedDateTime dataConclusao;
    private UUID diagnosticoId;

    @OneToOne
    @JoinColumn(name = "diagnosticoId", referencedColumnName = "id", updatable = false, insertable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private Diagnostico diagnostico;

    @Builder
    public OrdemServico(
            UUID clienteId,
            UUID veiculoId
    ) {
        this.id = UUID.randomUUID();
        this.clienteId = clienteId;
        this.veiculoId = veiculoId;
        this.status = OrdemServicoStatus.CRIADA;
        this.dataCriacao = DateTimeUtils.getNow();
        this.dataConclusao = DateTimeUtils.getNow().plusDays(1);
    }
}