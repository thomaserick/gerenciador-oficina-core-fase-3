package com.fiap.pj.core.customer.app;


import com.fiap.pj.core.customer.adapter.in.api.request.GetAlCustomerRequest;
import com.fiap.pj.core.customer.adapter.in.api.response.CustomerReponse;
import com.fiap.pj.core.customer.domain.CustomerDomainRepository;
import com.fiap.pj.core.customer.usecase.GetAllCustomerUseCase;
import com.fiap.pj.infra.api.Slice;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class GetAllCustomerService implements GetAllCustomerUseCase {

    private final CustomerDomainRepository repository;

    @Override
    public Slice<CustomerReponse> handle(GetAlCustomerRequest request) {
        return repository.findProjectedBy(request.buildSpecification(), request.getPageable(), CustomerReponse.class);
    }
}
