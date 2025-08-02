package com.fiap.pj.core.cliente.adapter.in.api;

import com.fiap.pj.core.cliente.adapter.in.api.openapi.ClienteControllerOpenApi;
import com.fiap.pj.core.cliente.adapter.in.api.request.ListarClienteRequest;
import com.fiap.pj.core.cliente.adapter.in.api.response.ClienteResponse;
import com.fiap.pj.core.cliente.usecase.AlterarClienteUserCase;
import com.fiap.pj.core.cliente.usecase.AtivarClienteUserCase;
import com.fiap.pj.core.cliente.usecase.CriarClienteUserCase;
import com.fiap.pj.core.cliente.usecase.InativarClienteUserCase;
import com.fiap.pj.core.cliente.usecase.ListarClienteUseCase;
import com.fiap.pj.core.cliente.usecase.command.AlterarClienteCommand;
import com.fiap.pj.core.cliente.usecase.command.AtivarClienteCommand;
import com.fiap.pj.core.cliente.usecase.command.CriarClienteCommand;
import com.fiap.pj.core.cliente.usecase.command.InativarClienteCommand;
import com.fiap.pj.core.sk.web.ResponseEntityUtils;
import com.fiap.pj.infra.api.Slice;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = ClienteController.PATH)
@AllArgsConstructor
public class ClienteController implements ClienteControllerOpenApi {

    public static final String PATH = "v1/clientes";

    private final CriarClienteUserCase criarClienteUserCase;
    private final AtivarClienteUserCase ativarClienteUserCase;
    private final InativarClienteUserCase inativarClienteUserCase;
    private final AlterarClienteUserCase alterarClienteUserCase;
    private final ListarClienteUseCase listarClienteUseCase;


    @Override
    @PostMapping
    public ResponseEntity<Void> criarCliente(@Valid @RequestBody CriarClienteCommand cmd) {
        var cliente = criarClienteUserCase.handle(cmd);
        return ResponseEntityUtils.create(getClass(), cliente.getId());
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Void> alterarCliente(@Valid @PathVariable UUID id, @RequestBody AlterarClienteCommand cmd) {
        cmd.setId(id);
        alterarClienteUserCase.handle(cmd);
        return ResponseEntity.ok().build();
    }

    @Override
    @PostMapping("/{id}/ativar")
    public ResponseEntity<Void> ativarCliente(@Valid @PathVariable UUID id) {
        ativarClienteUserCase.handle(new AtivarClienteCommand(id));
        return ResponseEntity.ok().build();
    }

    @Override
    @PostMapping("/{id}/inativar")
    public ResponseEntity<Void> inativarCliente(@Valid @PathVariable UUID id) {
        inativarClienteUserCase.handle(new InativarClienteCommand(id));
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping
    public Slice<ClienteResponse> listarCliente(@ParameterObject ListarClienteRequest filterRequest, @ParameterObject Pageable pageable) {
        filterRequest.setPageable(pageable);
        return listarClienteUseCase.handle(filterRequest);
    }


}
