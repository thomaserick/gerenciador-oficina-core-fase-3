package com.fiap.pj.core.vehicle.adapter.in.api;


import com.fiap.pj.core.customer.adapter.in.api.CustomerVehicleController;
import com.fiap.pj.core.customer.util.factory.CustomerTestFactory;
import com.fiap.pj.core.util.TestUtils;
import com.fiap.pj.core.vehicle.usecase.AddVehicleToCustomerUseCase;
import com.fiap.pj.core.vehicle.util.factory.VehicleTestFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.fiap.pj.core.vehicle.util.factory.VehicleTestFactory.oneAddVehicleToCustomerCommand;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class VehicleControllerTest {

    @Mock
    private AddVehicleToCustomerUseCase addVehicleToCustomerUseCase;

    @InjectMocks
    private CustomerVehicleController customerVehicleController;

    private MockMvc mock;

    @BeforeEach
    void setup() {
        mock = MockMvcBuilders.standaloneSetup(customerVehicleController).build();
    }

    @Test
    void shouldCreateCustomer() throws Exception {
        mock.perform(post(
                TestUtils.buildURL(CustomerVehicleController.PATH.replace("{id}", VehicleTestFactory.ID.toString())))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtils.objectToJson(oneAddVehicleToCustomerCommand(CustomerTestFactory.ID)))).andExpect(status().is2xxSuccessful());
    }

}
