package com.fiap.pj.ordemservico.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

}