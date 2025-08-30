package com.fiap.pj.core.ordemservico.domain;


import com.fiap.pj.core.ordemservico.domain.enums.OrdemServicoStatus;
import com.fiap.pj.core.util.DateTimeUtils;
import com.fiap.pj.infra.usuario.persistence.UsuarioEntity;
import com.fiap.pj.infra.util.security.SecurityContextUtils;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "situacoes_ordens_servico")
@NoArgsConstructor
@Getter
public class SituacaoOrdemServico {

    @Id
    private UUID id;

    @Enumerated(EnumType.STRING)
    private OrdemServicoStatus status;

    private UUID usuarioId;

    private UUID ordemServicoId;

    private ZonedDateTime dataCriacao;

    @OneToOne
    @JoinColumn(name = "usuarioId", referencedColumnName = "id", insertable = false, updatable = false)
    private UsuarioEntity usuario;

    public SituacaoOrdemServico(OrdemServicoStatus status, UUID ordemServicoId) {
        this.id = UUID.randomUUID();
        this.status = status;
        this.usuarioId = SecurityContextUtils.getUsuarioId();
        this.ordemServicoId = ordemServicoId;
        this.dataCriacao = DateTimeUtils.getNow();
    }
}
