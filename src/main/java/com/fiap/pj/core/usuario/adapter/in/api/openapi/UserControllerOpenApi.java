package com.fiap.pj.core.usuario.adapter.in.api.openapi;

import com.fiap.pj.core.usuario.usecase.command.CreateUserCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

public interface UserControllerOpenApi {

    @Operation(description = "Cria um novo usuário", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Usuario criado."),
            @ApiResponse(responseCode = "400", description = "O  Usuário não pode ser criado.")})
    ResponseEntity<Void> createUser(@Valid @RequestBody CreateUserCommand cmd);

    @Operation(description = "Desativar um novo usuário", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Usuario desativado."),
            @ApiResponse(responseCode = "400", description = "O  Usuário não pode ser desativado.")})
    public ResponseEntity<Void> disableUser(@PathVariable UUID id);
}
