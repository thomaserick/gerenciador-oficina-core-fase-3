package com.fiap.pj.core.customer.usecase;

import com.fiap.pj.core.customer.adapter.in.api.request.GetAlCustomerRequest;
import com.fiap.pj.core.customer.adapter.in.api.response.CustomerResponse;
import com.fiap.pj.infra.api.Slice;

public interface GetAllCustomerUseCase {

    Slice<CustomerResponse> handle(GetAlCustomerRequest request);
}
