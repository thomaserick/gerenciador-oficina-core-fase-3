package com.fiap.pj.core.ordemservico.domain;


import com.fiap.pj.core.ordemservico.domain.enums.OrdemServicoStatus;
import com.fiap.pj.core.util.security.SecurityContextUtils;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "situacao_ordem_servico")
@NoArgsConstructor
@Getter
public class SituacaoOrdemServico {

    @Id
    private UUID id;

    @Enumerated(EnumType.STRING)
    private OrdemServicoStatus status;

    private UUID usuarioId;

    private UUID ordemServicoId;

    public SituacaoOrdemServico(OrdemServicoStatus status, UUID ordemServicoId) {
        this.id = UUID.randomUUID();
        this.status = status;
        this.usuarioId = SecurityContextUtils.getUsuarioId();
        this.ordemServicoId = ordemServicoId;
    }
}
