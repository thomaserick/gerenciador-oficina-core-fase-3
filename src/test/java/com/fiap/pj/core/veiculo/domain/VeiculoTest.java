package com.fiap.pj.core.veiculo.domain;

import com.fiap.pj.core.cliente.util.factory.ClienteTestFactory;
import com.fiap.pj.core.veiculo.util.factory.VeiculoTestFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.fiap.pj.core.veiculo.util.factory.VeiculoTestFactory.ANO;
import static com.fiap.pj.core.veiculo.util.factory.VeiculoTestFactory.ID;
import static com.fiap.pj.core.veiculo.util.factory.VeiculoTestFactory.MARCA;
import static com.fiap.pj.core.veiculo.util.factory.VeiculoTestFactory.MODELO;
import static com.fiap.pj.core.veiculo.util.factory.VeiculoTestFactory.PLACA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VeiculoTest {

    @Test
    @DisplayName("Deve criar com sucesso uma instância de veiculo.")
    void deveCriarVeiculo() {

        var veiculo = VeiculoTestFactory.umVeiculo(ClienteTestFactory.ID);

        assertEquals(ID, veiculo.getId());
        assertEquals(PLACA, veiculo.getPlaca());
        assertEquals(MODELO, veiculo.getModelo());
        assertEquals(MARCA, veiculo.getMarca());
        assertEquals(ANO, veiculo.getAno());
        assertEquals(ClienteTestFactory.ID, veiculo.getClienteId());

    }

    @Nested
    class CreationFailure {

        @Test
        void deveFalharComPlacaInvalido() {
            assertThrows(NullPointerException.class,
                    () -> new Veiculo(ID, null, null, null, 0, ClienteTestFactory.ID));
        }

        @Test
        void deveFalharComClienteIdInvalido() {
            assertThrows(NullPointerException.class,
                    () -> new Veiculo(ID, PLACA, MODELO, MARCA, ANO, null));
        }
    }
}
