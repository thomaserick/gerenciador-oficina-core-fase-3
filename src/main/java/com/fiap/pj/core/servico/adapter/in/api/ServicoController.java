package com.fiap.pj.core.servico.adapter.in.api;

import com.fiap.pj.core.servico.adapter.in.api.openapi.ServicoControllerOpenApi;
import com.fiap.pj.core.servico.adapter.in.api.request.ListarServicoRequest;
import com.fiap.pj.core.servico.adapter.in.api.response.ServicoResponse;
import com.fiap.pj.core.servico.usecase.CriarServicoUseCase;
import com.fiap.pj.core.servico.usecase.ListarServicoUseCase;
import com.fiap.pj.core.servico.usecase.command.CriarServicoCommand;
import com.fiap.pj.core.sk.web.ResponseEntityUtils;
import com.fiap.pj.infra.api.Slice;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = ServicoController.PATH)
@AllArgsConstructor
public class ServicoController implements ServicoControllerOpenApi {

    public static final String PATH = "v1/servicos";

    private final CriarServicoUseCase criarServicoUseCase;
    private final ListarServicoUseCase listarServicoUseCase;

    @Override
    @PostMapping
    public ResponseEntity<Void> createService(CriarServicoCommand cmd) {
        var service = criarServicoUseCase.handle(cmd);
        return ResponseEntityUtils.create(getClass(), service.getId());
    }


    @Override
    @GetMapping
    public Slice<ServicoResponse> getAll(@ParameterObject ListarServicoRequest filterRequest, @ParameterObject Pageable pageable) {
        filterRequest.setPageable(pageable);
        return listarServicoUseCase.handle(filterRequest);
    }

}
