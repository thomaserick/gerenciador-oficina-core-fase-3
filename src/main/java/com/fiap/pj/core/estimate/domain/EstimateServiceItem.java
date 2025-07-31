package com.fiap.pj.core.estimate.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

import static java.util.Objects.requireNonNull;


@Entity
@Table(name = "estimates_services")
@NoArgsConstructor
@Getter
public class EstimateServiceItem {

    @Id
    private UUID id;
    private UUID serviceId;
    private UUID estimateId;
    private String description;
    private BigDecimal price;
    private BigDecimal quantity;

    @Builder
    public EstimateServiceItem(UUID id, UUID serviceId, UUID estimateId, String description, BigDecimal price, BigDecimal quantity) {
        this.id = requireNonNull(id);
        this.serviceId = requireNonNull(serviceId);
        this.estimateId = requireNonNull(estimateId);
        this.description = requireNonNull(description);
        this.price = price;
        this.quantity = quantity;
    }

    public BigDecimal totalCost() {
        return this.getPrice().multiply(this.getQuantity());
    }
}
