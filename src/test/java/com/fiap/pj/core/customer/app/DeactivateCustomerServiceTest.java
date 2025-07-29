package com.fiap.pj.core.customer.app;


import com.fiap.pj.core.customer.adapter.out.CustomerRepositoryJpa;
import com.fiap.pj.core.customer.domain.Customer;
import com.fiap.pj.core.customer.exception.CustomerExceptions.CustomerNotFoundException;
import com.fiap.pj.core.customer.usecase.command.DeactivateCustomerCommand;
import com.fiap.pj.core.customer.util.factory.CustomerTestFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeactivateCustomerServiceTest {

    @Mock
    private CustomerRepositoryJpa customerRepositoryJpa;

    @InjectMocks
    private DeactivateCustomerService deactivateCustomerService;

    @Test
    void shouldActivateUCustomer() {
        var id = UUID.randomUUID();
        when(customerRepositoryJpa.findByIdOrThrowNotFound(id)).thenReturn(CustomerTestFactory.oneCustomer());
        deactivateCustomerService.handle(new DeactivateCustomerCommand(id));
        verify(customerRepositoryJpa).save(Mockito.any(Customer.class));
    }

    @Test
    void shouldReturnCustomerNotFoundException() {
        var id = UUID.randomUUID();

        Mockito.doThrow(new CustomerNotFoundException())
                .when(customerRepositoryJpa)
                .findByIdOrThrowNotFound(id);

        var thrown = catchThrowable(() -> deactivateCustomerService.handle(new DeactivateCustomerCommand(id)));
        assertThat(thrown).isInstanceOf(CustomerNotFoundException.class);

    }


}
