package com.fiap.pj.core.servico.app;


import com.fiap.pj.core.servico.adapter.in.api.request.ListarServicoRequest;
import com.fiap.pj.core.servico.adapter.in.api.response.ServicoResponse;
import com.fiap.pj.core.servico.domain.ServicoDomainRepository;
import com.fiap.pj.core.servico.usecase.ListarServicoUseCase;
import com.fiap.pj.infra.sk.api.Slice;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class ListarServicoService implements ListarServicoUseCase {

    private final ServicoDomainRepository repository;

    @Override
    public Slice<ServicoResponse> handle(ListarServicoRequest request) {
        return repository.findProjectedBy(request.buildSpecification(), request.getPageable(), ServicoResponse.class);
    }
}
