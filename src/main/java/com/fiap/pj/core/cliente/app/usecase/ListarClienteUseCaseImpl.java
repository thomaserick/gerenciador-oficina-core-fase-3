package com.fiap.pj.core.cliente.app.usecase;


import com.fiap.pj.infra.cliente.controller.request.ListarClienteRequest;
import com.fiap.pj.infra.cliente.controller.response.ClienteResponse;
import com.fiap.pj.infra.sk.api.Slice;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class ListarClienteUseCaseImpl implements ListarClienteUseCase {


    @Override
    public Slice<ClienteResponse> handle(ListarClienteRequest request) {
        return null; // this.repository.findProjectedBy(request.buildSpecification(), request.getPageable(), ClienteResponse.class);
    }
}
