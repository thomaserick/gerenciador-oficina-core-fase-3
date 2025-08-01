package com.fiap.pj.core.service.app;


import com.fiap.pj.core.service.adapter.in.api.request.GetAllServiceRequest;
import com.fiap.pj.core.service.adapter.in.api.response.ServiceResponse;
import com.fiap.pj.core.service.domain.ServiceDomainRepository;
import com.fiap.pj.core.service.usecase.GetAllServiceUseCase;
import com.fiap.pj.infra.api.Slice;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class GetAllServiceService implements GetAllServiceUseCase {

    private final ServiceDomainRepository repository;

    @Override
    public Slice<ServiceResponse> handle(GetAllServiceRequest request) {
        return repository.findProjectedBy(request.buildSpecification(), request.getPageable(), ServiceResponse.class);
    }
}
