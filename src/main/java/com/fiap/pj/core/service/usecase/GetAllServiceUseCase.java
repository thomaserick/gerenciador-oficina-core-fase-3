package com.fiap.pj.core.service.usecase;

import com.fiap.pj.core.service.adapter.in.api.request.GetAllServiceRequest;
import com.fiap.pj.core.service.adapter.in.api.response.ServiceResponse;
import com.fiap.pj.infra.api.Slice;

public interface GetAllServiceUseCase {

    Slice<ServiceResponse> handle(GetAllServiceRequest request);
}
