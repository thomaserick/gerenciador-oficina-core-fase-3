package com.fiap.pj.core.cliente.app;


import com.fiap.pj.core.cliente.adapter.in.api.request.ListarClienteRequest;
import com.fiap.pj.core.cliente.adapter.in.api.response.ClienteResponse;
import com.fiap.pj.core.cliente.domain.ClienteDomainRepository;
import com.fiap.pj.core.cliente.usecase.ListarClienteUseCase;
import com.fiap.pj.infra.sk.api.Slice;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class ListarClienteService implements ListarClienteUseCase {

    private final ClienteDomainRepository repository;

    @Override
    public Slice<ClienteResponse> handle(ListarClienteRequest request) {
        return this.repository.findProjectedBy(request.buildSpecification(), request.getPageable(), ClienteResponse.class);
    }
}
