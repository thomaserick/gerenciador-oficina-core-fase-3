package com.fiap.pj.core.service.usecase;

import com.fiap.pj.core.service.domain.ServiceEntity;
import com.fiap.pj.core.service.usecase.command.CreateServiceCommand;

public interface CreateServiceUseCase {

    ServiceEntity handle(CreateServiceCommand cmd);
}
