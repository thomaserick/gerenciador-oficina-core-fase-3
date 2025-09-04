package com.fiap.pj.infra.ordemservico.gateways;

import com.fiap.pj.core.ordemservico.domain.Diagnostico;
import com.fiap.pj.infra.ordemservico.persistence.DiagnosticoEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DiagnosticoRepositoryMapper {

    public static DiagnosticoEntity mapToTable(Diagnostico diagnostico) {
        return new DiagnosticoEntity(
                diagnostico.getId(),
                diagnostico.getOrdemServicoId(),
                diagnostico.getDescricao(),
                diagnostico.getDataCriacao()
        );
    }

    public static Diagnostico mapToDomain(DiagnosticoEntity entity) {
        return new Diagnostico(
                entity.getId(),
                entity.getDescricao(),
                entity.getOrdemServicoId(),
                entity.getDataCriacao()
        );
    }
}
