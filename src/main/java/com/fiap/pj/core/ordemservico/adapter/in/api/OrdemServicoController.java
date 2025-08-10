package com.fiap.pj.core.ordemservico.adapter.in.api;

import com.fiap.pj.core.ordemservico.adapter.in.api.openapi.OrdemServicoControllerOpenApi;
import com.fiap.pj.core.ordemservico.adapter.in.api.request.ListarOrdemServicoRequest;
import com.fiap.pj.core.ordemservico.adapter.in.api.response.OrdemServicoResponse;
import com.fiap.pj.core.ordemservico.usecase.ListarOrdemServicoUseCase;
import com.fiap.pj.infra.api.Slice;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = OrdemServicoController.PATH)
@AllArgsConstructor
public class OrdemServicoController implements OrdemServicoControllerOpenApi {

    public static final String PATH = "v1/ordens-servicos";

    private final ListarOrdemServicoUseCase listarClienteUseCase;

    @Override
    @PostMapping
    public Slice<OrdemServicoResponse> listarOrdemServico(@ParameterObject ListarOrdemServicoRequest filterRequest, @ParameterObject Pageable pageable) {
        filterRequest.setPageable(pageable);
        return this.listarClienteUseCase.handle(filterRequest);
    }
}