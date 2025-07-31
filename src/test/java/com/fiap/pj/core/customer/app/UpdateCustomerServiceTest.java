package com.fiap.pj.core.customer.app;


import com.fiap.pj.core.customer.adapter.out.db.CustomerRepositoryJpa;
import com.fiap.pj.core.customer.domain.Customer;
import com.fiap.pj.core.customer.exception.CustomerExceptions.CustomerNotFoundException;
import com.fiap.pj.core.customer.util.factory.CustomerTestFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UpdateCustomerServiceTest {

    @Captor
    ArgumentCaptor<Customer> customerCaptor;
    @Mock
    private CustomerRepositoryJpa customerRepositoryJpa;
    @InjectMocks
    private UpdateCustomerService updateCustomerService;

    @Test
    void shouldUpdateCustomer() {
        var customer = CustomerTestFactory.oneCustomer();
        when(customerRepositoryJpa.findByIdOrThrowNotFound(customer.getId())).thenReturn(customer);

        updateCustomerService.handle(CustomerTestFactory.onUpdateCustomerCommand(customer.getId()));

        verify(customerRepositoryJpa).save(customerCaptor.capture());
        Customer customerUpdated = customerCaptor.getValue();

        assertNotNull(customerUpdated);
        assertEquals(CustomerTestFactory.ID, customerUpdated.getId());
        assertEquals(CustomerTestFactory.ALTER_NAME, customerUpdated.getName());
        assertEquals(CustomerTestFactory.ALTER_PHONE, customerUpdated.getPhone());
        assertEquals(CustomerTestFactory.ALTER_E_MAIL, customerUpdated.getEmail());
        assertEquals(CustomerTestFactory.ALTER_ADDRESS, customerUpdated.getAddress());


        verify(customerRepositoryJpa).save(customer);

    }

    @Test
    void shouldReturnCustomerNotFoundException() {
        var customer = CustomerTestFactory.oneCustomer();

        Mockito.doThrow(new CustomerNotFoundException())
                .when(customerRepositoryJpa)
                .findByIdOrThrowNotFound(customer.getId());

        var thrown = catchThrowable(() -> updateCustomerService.handle(CustomerTestFactory.onUpdateCustomerCommand(customer.getId())));
        assertThat(thrown).isInstanceOf(CustomerNotFoundException.class);
    }
}
