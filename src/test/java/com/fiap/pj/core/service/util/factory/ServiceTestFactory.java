package com.fiap.pj.core.service.util.factory;

import com.fiap.pj.core.service.domain.ServiceEntity;
import com.fiap.pj.core.service.usecase.command.CreateServiceCommand;

import java.math.BigDecimal;
import java.util.UUID;

public class ServiceTestFactory {

    public static final UUID ID = UUID.randomUUID();
    public static final String DESCRIPTION = "Troca de Óleo";
    public static final BigDecimal PRICE = BigDecimal.valueOf(100);
    public static final String OBSERVATION = "XPTO";

    public static ServiceEntity oneService() {
        return new ServiceEntity(ID, DESCRIPTION, PRICE, OBSERVATION, true);
    }

    public static CreateServiceCommand oneCreateServiceCommand() {
        return new CreateServiceCommand(DESCRIPTION, PRICE, OBSERVATION);
    }

}
