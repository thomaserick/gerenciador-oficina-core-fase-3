package com.fiap.pj.core.veiculo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static java.util.Objects.requireNonNull;

@Entity
@Table(name = "veiculos")
@NoArgsConstructor
@Getter
public class Veiculo {

    @Id
    private UUID id;
    private String placa;
    private String modelo;
    private String marca;
    private int ano;
    private UUID clienteId;

    @Builder
    public Veiculo(UUID id, String placa, String modelo, String marca, int ano, UUID clienteId) {
        this.id = requireNonNull(id);
        this.placa = requireNonNull(placa);
        this.modelo = modelo;
        this.marca = marca;
        this.ano = ano;
        this.clienteId = requireNonNull(clienteId);
    }
}
