package com.fiap.pj.core.estimate.app;

import com.fiap.pj.core.customer.domain.CustomerDomainRepository;
import com.fiap.pj.core.estimate.domain.Estimate;
import com.fiap.pj.core.estimate.domain.EstimateDomainRepository;
import com.fiap.pj.core.estimate.domain.EstimateServiceItem;
import com.fiap.pj.core.estimate.domain.enums.EstimateStatus;
import com.fiap.pj.core.estimate.usecase.CreateEstimateUseCase;
import com.fiap.pj.core.estimate.usecase.command.CreateEstimateCommand;
import com.fiap.pj.core.estimate.usecase.command.EstimateServiceItemCommand;
import com.fiap.pj.core.service.domain.ServiceDomainRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;


@Service
@Transactional
@AllArgsConstructor
public class CreateEstimateService implements CreateEstimateUseCase {

    private final EstimateDomainRepository repository;
    private final CustomerDomainRepository customerDomainRepository;
    private final ServiceDomainRepository serviceDomainRepository;


    @Override
    public Estimate handle(CreateEstimateCommand cmd) {

        var customer = customerDomainRepository.findByIdOrThrowNotFound(cmd.getCustomerId());
        customer.validVehicle(cmd.getVehicleId());

        var estimate = Estimate.builder()
                .id(UUID.randomUUID())
                .description(cmd.getDescription())
                .customerId(cmd.getCustomerId())
                .vehicleId(cmd.getVehicleId())
                .status(EstimateStatus.PENDING_APPROVE)
                .odometer(cmd.getOdometer())
                .build();

        buildServiceItens(estimate, cmd.getServices());

        return repository.save(estimate);
    }

    private void buildServiceItens(Estimate estimate, Set<EstimateServiceItemCommand> services) {

        services.stream().forEach(cmd -> {

            var service = serviceDomainRepository.findByIdOrThrowNotFound(cmd.serviceId());
            var estimateServiceItem = EstimateServiceItem.builder().id(UUID.randomUUID())
                    .estimateId(estimate.getId())
                    .price(service.getPrice())
                    .quantity(cmd.quantity())
                    .description(service.getDescription())
                    .serviceId(service.getId())
                    .build();
            estimate.addLaborSevice(estimateServiceItem);
        });

    }


}
