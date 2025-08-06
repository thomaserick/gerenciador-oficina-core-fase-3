package com.fiap.pj.core.usuario.adapter.in.api;

import com.fiap.pj.core.usuario.adapter.in.api.openapi.UsuarioLoginControllerOpenApi;
import com.fiap.pj.core.usuario.adapter.in.api.response.LoginUsuarioResponse;
import com.fiap.pj.core.usuario.usecase.LoginUsuarioUseCase;
import com.fiap.pj.core.usuario.usecase.command.LoginUsuarioCommand;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = UsuarioLoginController.PATH)
@AllArgsConstructor
public class UsuarioLoginController implements UsuarioLoginControllerOpenApi {

    public static final String PATH = "v1/usuarios/login";

    private final LoginUsuarioUseCase loginUsuarioUseCase;

    @Override
    @PostMapping
    public ResponseEntity<LoginUsuarioResponse> loginUsuario(LoginUsuarioCommand cmd) {
        var login = loginUsuarioUseCase.handle(cmd);
        return ResponseEntity.ok(login);
    }


}
