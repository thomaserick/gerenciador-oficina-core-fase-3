package com.fiap.pj.core.vehicle.app;


import com.fiap.pj.core.customer.domain.CustomerDomainRepository;
import com.fiap.pj.core.vehicle.domain.Vehicle;
import com.fiap.pj.core.vehicle.domain.VehicleDomainRepository;
import com.fiap.pj.core.vehicle.exception.VehicleExceptions.VehiclePlateDuplicateException;
import com.fiap.pj.core.vehicle.usecase.AddVehicleToCustomerUseCase;
import com.fiap.pj.core.vehicle.usecase.command.AddVehicleToCustomerCommand;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class AddVehicleToCustomerService implements AddVehicleToCustomerUseCase {

    private final CustomerDomainRepository customerDomainRepository;
    private final VehicleDomainRepository repository;

    @Override
    public void handle(AddVehicleToCustomerCommand cmd) {

        if (repository.existsByPlate(cmd.plate())) {
            throw new VehiclePlateDuplicateException();
        }

        var customer = customerDomainRepository.findByIdOrThrowNotFound(cmd.customerId());

        var vehicle = Vehicle.builder().id(UUID.randomUUID())
                .plate(cmd.plate())
                .make(cmd.make())
                .model(cmd.model())
                .year(cmd.year())
                .customerId(customer.getId())
                .build();

        customer.addVehicle(vehicle);

        customerDomainRepository.save(customer);

    }
}
