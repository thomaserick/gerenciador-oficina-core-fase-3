package com.fiap.pj.core.customer.adapter.in.api;

import com.fiap.pj.core.customer.adapter.in.api.openapi.CustomerControllerOpenApi;
import com.fiap.pj.core.customer.usecase.ActivateCustomerUserCase;
import com.fiap.pj.core.customer.usecase.CreateCustomerUserCase;
import com.fiap.pj.core.customer.usecase.DisableCustomerUserCase;
import com.fiap.pj.core.customer.usecase.UpdateCustomerUserCase;
import com.fiap.pj.core.customer.usecase.command.ActivateCustomerCommand;
import com.fiap.pj.core.customer.usecase.command.CreateCustomerCommand;
import com.fiap.pj.core.customer.usecase.command.DisableCustomerCommand;
import com.fiap.pj.core.customer.usecase.command.UpdateCustomerCommand;
import com.fiap.pj.core.sk.web.ResponseEntityUtils;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = CustomerController.PATH)
@AllArgsConstructor
public class CustomerController implements CustomerControllerOpenApi {

    public static final String PATH = "customer";

    private final CreateCustomerUserCase createCustomerUserCase;
    private final ActivateCustomerUserCase activateCustomerUserCase;
    private final DisableCustomerUserCase disableCustomerUserCase;
    private final UpdateCustomerUserCase updateCustomerUserCase;

    @Override
    @PostMapping
    public ResponseEntity<Void> createCustomer(@Valid @RequestBody CreateCustomerCommand cmd) {
        var customer = createCustomerUserCase.handle(cmd);
        return ResponseEntityUtils.create(getClass(), customer.getId());
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCustomer(@Valid @PathVariable UUID id, @RequestBody UpdateCustomerCommand cmd) {
        cmd.setId(id);
        updateCustomerUserCase.handle(cmd);
        return null;
    }

    @Override
    @PostMapping("/{id}/activate")
    public ResponseEntity<Void> activateCustomer(@Valid @PathVariable UUID id) {
        activateCustomerUserCase.handle(new ActivateCustomerCommand(id));
        return ResponseEntity.ok().build();
    }

    @Override
    @PostMapping("/{id}/disable")
    public ResponseEntity<Void> disableCustomer(@Valid @PathVariable UUID id) {
        disableCustomerUserCase.handle(new DisableCustomerCommand(id));
        return ResponseEntity.ok().build();
    }


}
