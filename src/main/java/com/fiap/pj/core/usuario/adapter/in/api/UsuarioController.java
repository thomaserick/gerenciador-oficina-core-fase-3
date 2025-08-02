package com.fiap.pj.core.usuario.adapter.in.api;

import com.fiap.pj.core.sk.web.ResponseEntityUtils;
import com.fiap.pj.core.usuario.adapter.in.api.openapi.UsuarioControllerOpenApi;
import com.fiap.pj.core.usuario.adapter.in.api.request.ListarUsuarioRequest;
import com.fiap.pj.core.usuario.adapter.in.api.response.UsuarioReponse;
import com.fiap.pj.core.usuario.usecase.AlterarUsuarioUseCase;
import com.fiap.pj.core.usuario.usecase.AtivarUsuarioUseCase;
import com.fiap.pj.core.usuario.usecase.CriarUsuarioUseCase;
import com.fiap.pj.core.usuario.usecase.InativarUsuarioUseCase;
import com.fiap.pj.core.usuario.usecase.ListarUsuarioUseCase;
import com.fiap.pj.core.usuario.usecase.command.AlterarUsuarioCommand;
import com.fiap.pj.core.usuario.usecase.command.AtivarUsuarioCommand;
import com.fiap.pj.core.usuario.usecase.command.CriarUsuarioCommand;
import com.fiap.pj.core.usuario.usecase.command.InativarUsuarioCommand;
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
@RequestMapping(path = UsuarioController.PATH)
@AllArgsConstructor
public class UsuarioController implements UsuarioControllerOpenApi {

    public static final String PATH = "v1/usuarios";

    private CriarUsuarioUseCase criarUsuarioUseCase;
    private InativarUsuarioUseCase inativarUsuarioUseCase;
    private AtivarUsuarioUseCase ativarUsuarioUseCase;
    private AlterarUsuarioUseCase alterarUsuarioUseCase;
    private ListarUsuarioUseCase listarUsuarioUseCase;

    @PostMapping
    public ResponseEntity<Void> criarUsuario(@Valid @RequestBody CriarUsuarioCommand cmd) {
        var usuario = criarUsuarioUseCase.handle(cmd);
        return ResponseEntityUtils.create(getClass(), usuario.getId());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> alteraUsuario(@Valid @PathVariable UUID id, @RequestBody AlterarUsuarioCommand cmd) {
        cmd.setId(id);
        alterarUsuarioUseCase.handle(cmd);
        return ResponseEntity.ok().build();
    }

    @PostMapping("{id}/inativar")
    public ResponseEntity<Void> inativarUsuario(@PathVariable UUID id) {
        inativarUsuarioUseCase.handle(new InativarUsuarioCommand(id));
        return ResponseEntity.ok().build();
    }


    @PostMapping("{id}/ativar")
    public ResponseEntity<Void> ativarUsuario(@PathVariable UUID id) {
        ativarUsuarioUseCase.handle(new AtivarUsuarioCommand(id));
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping
    public Slice<UsuarioReponse> listarUsuario(@ParameterObject ListarUsuarioRequest filterRequest, @ParameterObject Pageable pageable) {
        filterRequest.setPageable(pageable);
        return listarUsuarioUseCase.handle(filterRequest);
    }


}
