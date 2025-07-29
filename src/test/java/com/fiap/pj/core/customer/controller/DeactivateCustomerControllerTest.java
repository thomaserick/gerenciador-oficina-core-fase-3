package com.fiap.pj.core.customer.controller;


import com.fiap.pj.core.customer.adapter.in.api.CustomerController;
import com.fiap.pj.core.customer.usecase.DeactivateCustomerUserCase;
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

import static com.fiap.pj.core.customer.util.factory.CustomerTestFactory.onCrateCustomerCommand;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class DeactivateCustomerControllerTest {

    @Mock
    private DeactivateCustomerUserCase deactivateCustomerUserCase;
    @InjectMocks
    private CustomerController customerController;

    private MockMvc mock;

    @BeforeEach
    void setup() {
        mock = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    void shouldActivateCustomer() throws Exception {

        mock.perform(post(
                TestUtils.buildURL(CustomerController.PATH, UUID.randomUUID().toString(), "deactivate"))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtils.objectToJson(onCrateCustomerCommand()))).andExpect(status().is2xxSuccessful());
    }

}
