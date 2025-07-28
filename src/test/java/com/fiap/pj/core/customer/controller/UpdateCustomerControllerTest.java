package com.fiap.pj.core.customer.controller;


import com.fiap.pj.core.customer.adapter.in.api.CustomerController;
import com.fiap.pj.core.customer.usecase.UpdateCustomerUserCase;
import com.fiap.pj.core.customer.util.factory.CustomerTestFactory;
import com.fiap.pj.core.util.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static com.fiap.pj.core.customer.util.factory.CustomerTestFactory.onUpdateCustomerCommand;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UpdateCustomerControllerTest {

    @Mock
    private UpdateCustomerUserCase updateCustomerUserCase;

    @InjectMocks
    private CustomerController customerController;

    private MockMvc mock;

    @BeforeEach
    void setup() {
        mock = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    void shouldUpdateCustomer() throws Exception {

        mock.perform(put(
                TestUtils.buildURL(CustomerController.PATH, UUID.randomUUID().toString()))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtils.objectToJson(onUpdateCustomerCommand(CustomerTestFactory.ID)))).andExpect(status().is2xxSuccessful());
    }


}
