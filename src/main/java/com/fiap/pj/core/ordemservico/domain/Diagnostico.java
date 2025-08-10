package com.fiap.pj.core.ordemservico.domain;

import com.fiap.pj.core.util.DateTimeUtils;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "diagnostico")
@NoArgsConstructor
@Getter
public class Diagnostico {

    @Id
    private UUID id;
    private String descricao;
    private UUID ordemServicoId;
    private ZonedDateTime dataCriacao;

    public Diagnostico(
            UUID ordemServicoId,
            String descricao
    ) {
        this.id = UUID.randomUUID();
        this.ordemServicoId = ordemServicoId;
        this.descricao = descricao;
        this.dataCriacao = DateTimeUtils.getNow();
    }

}