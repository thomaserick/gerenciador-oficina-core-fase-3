package com.fiap.pj.core.cliente.adapter.in.api;

import com.fiap.pj.core.cliente.adapter.in.api.openapi.ClienteVeiculoControllerOpenApi;
import com.fiap.pj.core.sk.web.ResponseEntityUtils;
import com.fiap.pj.core.sk.web.ResponseEntityUtils.ResponseId;
import com.fiap.pj.core.veiculo.usecase.AdicionarVeiculoClienteUseCase;
import com.fiap.pj.core.veiculo.usecase.command.AdicionarVeiculoClienteCommand;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = ClienteVeiculoController.PATH)
@AllArgsConstructor
public class ClienteVeiculoController implements ClienteVeiculoControllerOpenApi {

    public static final String PATH = "v1/clientes/{id}/veiculos";

    private final AdicionarVeiculoClienteUseCase adicionarVeiculoClienteUseCase;

    @Override
    @PostMapping
    public ResponseEntity<ResponseId> adicionarVeiculo(@PathVariable UUID id, @RequestBody AdicionarVeiculoClienteCommand cmd) {
        var veiculo = adicionarVeiculoClienteUseCase.handle(cmd.comClienteId(id));
        return ResponseEntityUtils.ok(veiculo.getId());
    }

}
