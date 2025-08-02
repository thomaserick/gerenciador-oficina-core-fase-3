package com.fiap.pj.core.veiculo.domain;

import com.fiap.pj.core.cliente.util.factory.ClienteTestFactory;
import com.fiap.pj.core.veiculo.util.factory.VehicleTestFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.fiap.pj.core.veiculo.util.factory.VehicleTestFactory.ID;
import static com.fiap.pj.core.veiculo.util.factory.VehicleTestFactory.MAKE;
import static com.fiap.pj.core.veiculo.util.factory.VehicleTestFactory.MODEL;
import static com.fiap.pj.core.veiculo.util.factory.VehicleTestFactory.PLATE;
import static com.fiap.pj.core.veiculo.util.factory.VehicleTestFactory.YEAR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VeiculoTest {

    @Test
    @DisplayName("Deve criar com sucesso uma instância de Cliente.")
    void shouldCrateCustomer() {

        var veiculo = VehicleTestFactory.oneVehicle(ClienteTestFactory.ID);

        assertEquals(ID, veiculo.getId());
        assertEquals(PLATE, veiculo.getPlaca());
        assertEquals(MODEL, veiculo.getModelo());
        assertEquals(MAKE, veiculo.getMarca());
        assertEquals(YEAR, veiculo.getAno());
        assertEquals(ClienteTestFactory.ID, veiculo.getClienteId());

    }

    @Nested
    class CreationFailure {

        @Test
        void ShouldNotCreateVehicleWithoutPlate() {
            assertThrows(NullPointerException.class,
                    () -> new Veiculo(ID, null, null, null, 0, ClienteTestFactory.ID));
        }

        @Test
        void ShouldNotCreateVehicleWithoutCustomerId() {
            assertThrows(NullPointerException.class,
                    () -> new Veiculo(ID, PLATE, MODEL, MAKE, YEAR, null));
        }
    }
}
