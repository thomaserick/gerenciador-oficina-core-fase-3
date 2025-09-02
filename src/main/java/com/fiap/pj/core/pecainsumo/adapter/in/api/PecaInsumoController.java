package com.fiap.pj.core.pecainsumo.adapter.in.api;

import com.fiap.pj.core.pecainsumo.adapter.in.api.openapi.PecaInsumoControllerOpenApi;
import com.fiap.pj.core.pecainsumo.adapter.in.api.request.ListarPecaInsumoRequest;
import com.fiap.pj.core.pecainsumo.adapter.in.api.response.PecaInsumoResponse;
import com.fiap.pj.core.pecainsumo.exception.PecaInsumoExceptions.PecaInsumoComRelacionamentoException;
import com.fiap.pj.core.pecainsumo.usecase.AlterarPecaInsumoUseCase;
import com.fiap.pj.core.pecainsumo.usecase.CriarPecaInsumoUseCase;
import com.fiap.pj.core.pecainsumo.usecase.ExcluirPecaInsumoUseCase;
import com.fiap.pj.core.pecainsumo.usecase.ListarPecaInsumoUseCase;
import com.fiap.pj.core.pecainsumo.usecase.command.AlterarPecaInsumoCommand;
import com.fiap.pj.core.pecainsumo.usecase.command.CriarPecaInsumoCommand;
import com.fiap.pj.core.pecainsumo.usecase.command.ExcluirPecaInsumoCommand;
import com.fiap.pj.infra.sk.api.Slice;
import com.fiap.pj.infra.sk.web.ResponseEntityUtils;
import com.fiap.pj.infra.sk.web.ResponseEntityUtils.ResponseId;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = PecaInsumoController.PATH)
@AllArgsConstructor
public class PecaInsumoController implements PecaInsumoControllerOpenApi {

    public static final String PATH = "v1/pecas-insumos";

    private final CriarPecaInsumoUseCase criarPecaInsumoUseCase;
    private final AlterarPecaInsumoUseCase alterarPecaInsumoUseCase;
    private final ListarPecaInsumoUseCase listarPecaInsumoUseCase;
    private final ExcluirPecaInsumoUseCase excluirPecaInsumoUseCase;

    @PostMapping
    public ResponseEntity<ResponseId> criarPecaInsumo(@Valid @RequestBody CriarPecaInsumoCommand cmd) {
        var pecaInsumo = criarPecaInsumoUseCase.handle(cmd);
        return ResponseEntityUtils.create(getClass(), pecaInsumo.getId());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> alterarPecaInsumo(@Valid @PathVariable UUID id, @RequestBody AlterarPecaInsumoCommand cmd) {
        cmd.setId(id);
        alterarPecaInsumoUseCase.handle(cmd);
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping
    public Slice<PecaInsumoResponse> listarPecaInsumo(@ParameterObject ListarPecaInsumoRequest filterRequest, @ParameterObject Pageable pageable) {
        filterRequest.setPageable(pageable);
        return listarPecaInsumoUseCase.handle(filterRequest);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPecaInsumo(@PathVariable UUID id) {
        try {
            excluirPecaInsumoUseCase.handle(new ExcluirPecaInsumoCommand(id));
        } catch (DataIntegrityViolationException e) {
            throw new PecaInsumoComRelacionamentoException();
        }
        return ResponseEntity.ok().build();
    }
} 