package com.fiap.pj.core.service.domain;

import com.fiap.pj.core.service.util.factory.ServiceTestFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ServiceTest {


    @Test
    @DisplayName("Deve criar com sucesso uma instância de servico.")
    void shouldCrateService() {
        var service = ServiceTestFactory.oneService();


        assertEquals(ServiceTestFactory.ID, service.getId());
        assertEquals(ServiceTestFactory.DESCRIPTION, service.getDescription());
        assertEquals(ServiceTestFactory.PRICE, service.getPrice());
        assertEquals(ServiceTestFactory.OBSERVATION, service.getObservation());
        assertTrue(service.isActive());
    }

    @Nested
    class CreationFailure {

        @Test
        void ShouldNotCreateServiceWithoutId() {
            assertThrows(NullPointerException.class,
                    () -> new ServiceEntity(null,
                            null,
                            null,
                            null, true));
        }

        @Test
        void ShouldNotCreateServiceWithoutDescription() {
            assertThrows(NullPointerException.class,
                    () -> new ServiceEntity(ServiceTestFactory.ID,
                            null,
                            null,
                            null, true));
        }


    }
}
