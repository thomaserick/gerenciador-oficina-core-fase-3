package com.fiap.pj.core.customer.app;


import com.fiap.pj.core.customer.adapter.out.CustomerRepositoryJpa;
import com.fiap.pj.core.customer.domain.Customer;
import com.fiap.pj.core.customer.exception.CustomerExceptions.CustomerNotFoundException;
import com.fiap.pj.core.customer.usecase.command.DisableCustomerCommand;
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
class DisableCustomerServiceTest {

    @Mock
    private CustomerRepositoryJpa customerRepositoryJpa;

    @InjectMocks
    private DisableCustomerService disableCustomerService;

    @Test
    void shouldActivateUCustomer() {
        var id = UUID.randomUUID();
        when(customerRepositoryJpa.findByIdOrThrowNotFound(id)).thenReturn(CustomerTestFactory.oneCustomer());
        disableCustomerService.handle(new DisableCustomerCommand(id));
        verify(customerRepositoryJpa).save(Mockito.any(Customer.class));
    }

    @Test
    void shouldReturnCustomerNotFoundException() {
        var id = UUID.randomUUID();

        Mockito.doThrow(new CustomerNotFoundException())
                .when(customerRepositoryJpa)
                .findByIdOrThrowNotFound(id);

        var thrown = catchThrowable(() -> disableCustomerService.handle(new DisableCustomerCommand(id)));
        assertThat(thrown).isInstanceOf(CustomerNotFoundException.class);

    }


}
