package com.fiap.pj.core.service.app;


import com.fiap.pj.core.service.adapter.out.db.ServiceRepositoryJpa;
import com.fiap.pj.core.service.domain.ServiceEntity;
import com.fiap.pj.core.service.util.factory.ServiceTestFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateServiceServiceTest {

    @Mock
    private ServiceRepositoryJpa serviceRepositoryJpa;

    @InjectMocks
    private CreateServiceService createServiceService;

    @Test
    void shouldCreateService() {
        when(serviceRepositoryJpa.save(any(ServiceEntity.class))).thenReturn(ServiceTestFactory.oneService());

        var service = createServiceService.handle(ServiceTestFactory.oneCreateServiceCommand());

        assertNotNull(service);
        assertEquals(ServiceTestFactory.ID, service.getId());
        assertEquals(ServiceTestFactory.DESCRIPTION, service.getDescription());
        assertEquals(ServiceTestFactory.PRICE, service.getPrice());
        assertEquals(ServiceTestFactory.OBSERVATION, service.getObservation());

    }
}
