package com.fiap.pj.core.vehicle.domain;

import com.fiap.pj.core.customer.util.factory.CustomerTestFactory;
import com.fiap.pj.core.vehicle.util.factory.VehicleTestFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.fiap.pj.core.vehicle.util.factory.VehicleTestFactory.ID;
import static com.fiap.pj.core.vehicle.util.factory.VehicleTestFactory.MAKE;
import static com.fiap.pj.core.vehicle.util.factory.VehicleTestFactory.MODEL;
import static com.fiap.pj.core.vehicle.util.factory.VehicleTestFactory.PLATE;
import static com.fiap.pj.core.vehicle.util.factory.VehicleTestFactory.YEAR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VehicleTest {

    @Test
    @DisplayName("Deve criar com sucesso uma instância de Cliente.")
    void shouldCrateCustomer() {

        var vehicle = VehicleTestFactory.oneVehicle(CustomerTestFactory.ID);

        assertEquals(ID, vehicle.getId());
        assertEquals(PLATE, vehicle.getPlate());
        assertEquals(MODEL, vehicle.getModel());
        assertEquals(MAKE, vehicle.getMake());
        assertEquals(YEAR, vehicle.getYear());
        assertEquals(CustomerTestFactory.ID, vehicle.getCustomerId());

    }

    @Nested
    class CreationFailure {

        @Test
        void ShouldNotCreateVehicleWithoutPlate() {
            assertThrows(NullPointerException.class,
                    () -> new Vehicle(ID, null, null, null, 0, CustomerTestFactory.ID));
        }

        @Test
        void ShouldNotCreateVehicleWithoutCustomerId() {
            assertThrows(NullPointerException.class,
                    () -> new Vehicle(ID, PLATE, MODEL, MAKE, YEAR, null));
        }
    }
}
