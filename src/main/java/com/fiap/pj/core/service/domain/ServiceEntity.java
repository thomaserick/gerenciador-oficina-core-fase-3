package com.fiap.pj.core.service.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

@Entity
@Table(name = "service")
@NoArgsConstructor
@Getter
public class ServiceEntity {

    @Id
    private UUID id;
    private String description;
    private BigDecimal price;
    private String observation;
    private boolean active;

    public ServiceEntity(UUID id, String description, BigDecimal price, String observation, boolean active) {
        this.id = requireNonNull(id);
        this.description = requireNonNull(description);
        this.price = price;
        this.observation = observation;
        this.active = active;
    }
}
