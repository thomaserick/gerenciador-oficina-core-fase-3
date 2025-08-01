package com.fiap.pj.core.customer.usecase;

import com.fiap.pj.core.customer.adapter.in.api.request.GetAlCustomerRequest;
import com.fiap.pj.core.customer.adapter.in.api.response.CustomerReponse;
import com.fiap.pj.infra.api.Slice;

public interface GetAllCustomerUseCase {

    Slice<CustomerReponse> handle(GetAlCustomerRequest request);
}
