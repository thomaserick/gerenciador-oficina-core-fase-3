package com.fiap.pj.core.ordemservico.adapter.in.api;

import com.fiap.pj.core.ordemservico.adapter.in.api.openapi.OrdemServicoControllerOpenApi;
import com.fiap.pj.core.ordemservico.adapter.in.api.request.ListarOrdemServicoRequest;
import com.fiap.pj.core.ordemservico.adapter.in.api.response.OrdemServicoResponse;
import com.fiap.pj.core.ordemservico.usecase.ListarOrdemServicoUseCase;
import com.fiap.pj.core.ordemservico.usecase.RealizarDiagnosticoOrdemServicoUseCase;
import com.fiap.pj.core.ordemservico.usecase.command.RealizarDiagnosticoOrdemServicoCommand;
import com.fiap.pj.infra.api.Slice;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = OrdemServicoController.PATH)
@AllArgsConstructor
public class OrdemServicoController implements OrdemServicoControllerOpenApi {

    public static final String PATH = "v1/ordens-servicos";

    private final ListarOrdemServicoUseCase listarClienteUseCase;
    private final RealizarDiagnosticoOrdemServicoUseCase realizarDiagnosticoOrdemServicoUseCase;

    @Override
    @PostMapping
    public Slice<OrdemServicoResponse> listarOrdemServico(@ParameterObject ListarOrdemServicoRequest filterRequest, @ParameterObject Pageable pageable) {
        filterRequest.setPageable(pageable);
        return this.listarClienteUseCase.handle(filterRequest);
    }

    @Override
    @PostMapping("/{id}/ralizar-diagnostico")
    public ResponseEntity<Void> realizarDiagnosticoOrdemServico(@Valid @PathVariable UUID id, @Valid @RequestBody RealizarDiagnosticoOrdemServicoCommand cmd) {
        cmd.setOrdemServicoId(id);
        this.realizarDiagnosticoOrdemServicoUseCase.handle(cmd);
        return ResponseEntity.ok().build();
    }
}