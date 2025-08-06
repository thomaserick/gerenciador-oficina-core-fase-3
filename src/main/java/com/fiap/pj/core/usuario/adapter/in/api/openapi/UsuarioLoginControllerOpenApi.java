package com.fiap.pj.core.usuario.adapter.in.api.openapi;

import com.fiap.pj.core.usuario.adapter.in.api.response.LoginUsuarioResponse;
import com.fiap.pj.core.usuario.usecase.command.LoginUsuarioCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface UsuarioLoginControllerOpenApi {

    @Operation(description = "Realiza o login do usuário", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Login realizado com sucesso."),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas.")})
    ResponseEntity<LoginUsuarioResponse> loginUsuario(@RequestBody LoginUsuarioCommand cmd);

}
