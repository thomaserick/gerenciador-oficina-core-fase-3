package com.fiap.pj.core.vehicle.app;


import com.fiap.pj.core.customer.adapter.out.CustomerRepositoryJpa;
import com.fiap.pj.core.customer.domain.Customer;
import com.fiap.pj.core.customer.util.factory.CustomerTestFactory;
import com.fiap.pj.core.vehicle.adapter.out.db.VehicleRepositoryJpa;
import com.fiap.pj.core.vehicle.exception.VehicleExceptions.VehiclePlateDuplicateException;
import com.fiap.pj.core.vehicle.util.factory.VehicleTestFactory;
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
class AddVehicleToCustomerServiceTest {

    @Captor
    ArgumentCaptor<Customer> customerCaptor;
    @Mock
    private CustomerRepositoryJpa customerRepositoryJpa;
    @Mock
    private VehicleRepositoryJpa vehicleRepositoryJpa;

    @InjectMocks
    private AddVehicleToCustomerService addVehicleToCustomerService;

    @Test
    void shouldAddVehicleToCustomer() {
        var customer = CustomerTestFactory.oneCustomer();
        when(customerRepositoryJpa.findByIdOrThrowNotFound(customer.getId())).thenReturn(customer);
        addVehicleToCustomerService.handle(VehicleTestFactory.oneAddVehicleToCustomerCommand(customer.getId()));

        verify(customerRepositoryJpa).save(customerCaptor.capture());
        Customer customerUpdated = customerCaptor.getValue();

        assertNotNull(customerUpdated);
        var vehicle = customerUpdated.getVehicles().stream().findFirst().orElse(null);
        assertNotNull(vehicle);
        assertEquals(VehicleTestFactory.PLATE, vehicle.getPlate());
        assertEquals(VehicleTestFactory.MAKE, vehicle.getMake());
        assertEquals(VehicleTestFactory.MODEL, vehicle.getModel());
        assertEquals(VehicleTestFactory.YEAR, vehicle.getYear());
    }

    @Test
    void shouldReturnVehiclePlateDuplicateException() {
        var vehicle = VehicleTestFactory.oneVehicle(CustomerTestFactory.ID);
        Mockito.when(vehicleRepositoryJpa.existsByPlate(vehicle.getPlate())).thenReturn(true);
        var thrown = catchThrowable(() -> addVehicleToCustomerService.handle(VehicleTestFactory.oneAddVehicleToCustomerCommand(CustomerTestFactory.ID)));
        assertThat(thrown).isInstanceOf(VehiclePlateDuplicateException.class);
    }

}
