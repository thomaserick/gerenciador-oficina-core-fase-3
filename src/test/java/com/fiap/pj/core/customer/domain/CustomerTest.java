package com.fiap.pj.core.customer.domain;

import com.fiap.pj.core.customer.util.factory.CustomerTestFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.fiap.pj.core.customer.util.factory.CustomerTestFactory.ADDRESS;
import static com.fiap.pj.core.customer.util.factory.CustomerTestFactory.IDENTIFICATION_DOCUMENT_NUMBER;
import static com.fiap.pj.core.customer.util.factory.CustomerTestFactory.PHONE;
import static com.fiap.pj.core.usuario.util.factrory.UserTestFactory.E_MAIL;
import static com.fiap.pj.core.usuario.util.factrory.UserTestFactory.NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomerTest {


    @Test
    @DisplayName("Deve criar com sucesso uma instância de Cliente.")
    void shouldCrateCustomer() {

        var customer = CustomerTestFactory.oneCustomer();

        assertEquals(NAME, customer.getName());
        assertEquals(E_MAIL, customer.getEmail());
        assertEquals(PHONE, customer.getPhone());
        assertEquals(ADDRESS, customer.getAddress());
        assertEquals(IDENTIFICATION_DOCUMENT_NUMBER, customer.getIdentificationDocument().getNumber());
        assertTrue(customer.isActive());
    }
}
