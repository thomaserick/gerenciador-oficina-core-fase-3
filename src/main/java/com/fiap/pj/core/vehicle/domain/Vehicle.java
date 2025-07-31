package com.fiap.pj.core.vehicle.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static java.util.Objects.requireNonNull;

@Entity
@Table(name = "vehicles")
@NoArgsConstructor
@Getter
public class Vehicle {

    @Id
    private UUID id;
    private String plate;
    private String model;
    private String make;
    @Column(name = "model_year")
    private int year;
    private UUID customerId;

    @Builder
    public Vehicle(UUID id, String plate, String model, String make, int year, UUID customerId) {
        this.id = requireNonNull(id);
        this.plate = requireNonNull(plate);
        this.model = model;
        this.make = make;
        this.year = year;
        this.customerId = requireNonNull(customerId);
    }
}
