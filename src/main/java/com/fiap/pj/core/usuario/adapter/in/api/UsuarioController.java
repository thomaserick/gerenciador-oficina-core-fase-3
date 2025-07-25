package com.fiap.pj.core.usuario.adapter.in.api;

import com.fiap.pj.core.sk.web.ResponseEntityUtils;
import com.fiap.pj.core.usuario.adapter.in.api.openapi.UsuarioControllerOpenApi;
import com.fiap.pj.core.usuario.usecase.CriarUsuarioUseCase;
import com.fiap.pj.core.usuario.usecase.DesativarUsuarioUseCase;
import com.fiap.pj.core.usuario.usecase.command.CriarUsuarioCommand;
import com.fiap.pj.core.usuario.usecase.command.DesativarUsuarioCommand;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = UsuarioController.PATH)
@AllArgsConstructor
public class UsuarioController implements UsuarioControllerOpenApi {

    public static final String PATH = "usuarios";

    private CriarUsuarioUseCase criarUsuarioUseCase;
    private DesativarUsuarioUseCase desativarUsuarioUseCase;

    @PostMapping
    public ResponseEntity<Void> criarUsuario(@Valid @RequestBody CriarUsuarioCommand cmd) {
        var usuario = criarUsuarioUseCase.handle(cmd);
        return ResponseEntityUtils.create(getClass(), usuario.getId());
    }

    @PostMapping("{id}/desativar")
    public ResponseEntity<Void> desativarUsuario(@PathVariable UUID id) {
        desativarUsuarioUseCase.handle(new DesativarUsuarioCommand(id));
        return ResponseEntity.ok().build();
    }


}
