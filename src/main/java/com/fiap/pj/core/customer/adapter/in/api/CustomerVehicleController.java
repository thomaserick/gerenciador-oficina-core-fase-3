package com.fiap.pj.core.customer.adapter.in.api;

import com.fiap.pj.core.customer.adapter.in.api.openapi.CustomerVehicleControllerOpenApi;
import com.fiap.pj.core.vehicle.usecase.AddVehicleToCustomerUseCase;
import com.fiap.pj.core.vehicle.usecase.command.AddVehicleToCustomerCommand;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = CustomerVehicleController.PATH)
@AllArgsConstructor
public class CustomerVehicleController implements CustomerVehicleControllerOpenApi {

    public static final String PATH = "v1/customer/{id}/vechicles";

    private final AddVehicleToCustomerUseCase addVehicleToCustomerUseCase;

    @Override
    @PostMapping
    public ResponseEntity<Void> addVehicle(@PathVariable UUID id, @RequestBody AddVehicleToCustomerCommand cmd) {
        addVehicleToCustomerUseCase.handle(cmd.withCustomerId(id));
        return ResponseEntity.ok().build();
    }

}
