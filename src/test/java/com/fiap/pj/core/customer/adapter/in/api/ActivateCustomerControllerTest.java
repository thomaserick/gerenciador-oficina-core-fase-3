package com.fiap.pj.core.customer.adapter.in.api;


import com.fiap.pj.core.customer.usecase.ActivateCustomerUserCase;
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
class ActivateCustomerControllerTest {

    @Mock
    private ActivateCustomerUserCase activateCustomerUserCase;

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
                TestUtils.buildURL(CustomerController.PATH, UUID.randomUUID().toString(), "activate"))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtils.objectToJson(onCrateCustomerCommand()))).andExpect(status().is2xxSuccessful());
    }

}
