package com.fiap.pj.core.customer.app;


import com.fiap.pj.core.customer.adapter.out.db.CustomerRepositoryJpa;
import com.fiap.pj.core.customer.domain.Customer;
import com.fiap.pj.core.customer.exception.CustomerExceptions.DocumentIdentificationDuplicateException;
import com.fiap.pj.core.customer.util.factory.CustomerTestFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.fiap.pj.core.customer.util.factory.CustomerTestFactory.ADDRESS;
import static com.fiap.pj.core.customer.util.factory.CustomerTestFactory.IDENTIFICATION_DOCUMENT_NUMBER;
import static com.fiap.pj.core.customer.util.factory.CustomerTestFactory.PHONE;
import static com.fiap.pj.core.user.util.factrory.UserTestFactory.E_MAIL;
import static com.fiap.pj.core.user.util.factrory.UserTestFactory.NAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateCustomerServiceTest {

    @Mock
    private CustomerRepositoryJpa customerRepositoryJpa;

    @InjectMocks
    private CreateCustomerService createCustomerService;

    @Test
    void shouldCreateUser() {
        when(customerRepositoryJpa.save(any(Customer.class))).thenReturn(CustomerTestFactory.oneCustomer());

        var customer = createCustomerService.handle(CustomerTestFactory.onCrateCustomerCommand());

        assertEquals(NAME, customer.getName());
        assertEquals(E_MAIL, customer.getEmail());
        assertEquals(PHONE, customer.getPhone());
        assertEquals(ADDRESS, customer.getAddress());
        assertEquals(IDENTIFICATION_DOCUMENT_NUMBER, customer.getIdentificationDocument().getNumber());
        assertTrue(customer.isActive());
    }

    @Test
    void shouldReturnConsumerDocumentIdentificationDuplicateException() {

        Mockito.when(customerRepositoryJpa.existsByIdentificationDocumentNumber(Mockito.anyString())).thenReturn(true);

        var thrown = catchThrowable(() -> createCustomerService.handle(CustomerTestFactory.onCrateCustomerCommand()));
        assertThat(thrown).isInstanceOf(DocumentIdentificationDuplicateException.class);

    }
}
