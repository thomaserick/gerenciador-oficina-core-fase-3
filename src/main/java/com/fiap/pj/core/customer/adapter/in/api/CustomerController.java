package com.fiap.pj.core.customer.adapter.in.api;

import com.fiap.pj.core.customer.adapter.in.api.openapi.CustomerControllerOpenApi;
import com.fiap.pj.core.customer.usecase.CreateCustomerUserCase;
import com.fiap.pj.core.customer.usecase.command.CreateCustomerCommand;
import com.fiap.pj.core.sk.web.ResponseEntityUtils;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = CustomerController.PATH)
@AllArgsConstructor
public class CustomerController implements CustomerControllerOpenApi {

    public static final String PATH = "customer";

    private CreateCustomerUserCase createCustomerUserCase;


    @PostMapping
    public ResponseEntity<Void> createCustomer(@Valid @RequestBody CreateCustomerCommand cmd) {
        var customer = createCustomerUserCase.handle(cmd);
        return ResponseEntityUtils.create(getClass(), customer.getId());
    }


}
