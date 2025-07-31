package com.fiap.pj.core.estimate.usecase;

import com.fiap.pj.core.estimate.domain.Estimate;
import com.fiap.pj.core.estimate.usecase.command.CreateEstimateCommand;

public interface CreateEstimateUseCase {

    Estimate handle(CreateEstimateCommand cmd);
}
