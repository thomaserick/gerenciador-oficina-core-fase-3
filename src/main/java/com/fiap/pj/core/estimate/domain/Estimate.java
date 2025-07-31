package com.fiap.pj.core.estimate.domain;

import com.fiap.pj.core.estimate.domain.enums.EstimateStatus;
import com.fiap.pj.core.util.DateTimeUtils;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

@Entity
@Table(name = "estimates")
@NoArgsConstructor
@Getter
public class Estimate {

    @Id
    private UUID id;
    private String description;
    private UUID customerId;
    private UUID vehicleId;
    private int odometer;
    @Enumerated(EnumType.STRING)
    private EstimateStatus status;
    private UUID workOrderId;
    private ZonedDateTime createdAt;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "estimateId")
    private Set<EstimateServiceItem> laborServices = new HashSet<>();

    @Builder
    public Estimate(UUID id, String description, UUID customerId, UUID vehicleId, int odometer, EstimateStatus status, UUID workOrderId) {
        this.id = requireNonNull(id);
        this.description = description;
        this.customerId = requireNonNull(customerId);
        this.vehicleId = requireNonNull(vehicleId);
        this.odometer = odometer;
        this.status = requireNonNull(status);
        this.workOrderId = workOrderId;
        this.createdAt = DateTimeUtils.getNow();
    }

    public void addLaborSevice(EstimateServiceItem laborService) {
        this.laborServices.add(laborService);
    }

    public BigDecimal getTotalCost() {
        return this.getLaborServices().stream().map(EstimateServiceItem::totalCost).reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}
