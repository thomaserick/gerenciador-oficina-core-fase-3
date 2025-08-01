package com.fiap.pj.core.usuario.adapter.in.api.openapi;

import com.fiap.pj.core.usuario.adapter.in.api.request.GetAlUserRequest;
import com.fiap.pj.core.usuario.adapter.in.api.response.UserReponse;
import com.fiap.pj.core.usuario.usecase.command.CreateUserCommand;
import com.fiap.pj.core.usuario.usecase.command.UpdateUserCommand;
import com.fiap.pj.infra.api.Slice;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

public interface UserControllerOpenApi {

    @Operation(description = "Cria um novo usuário", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Usuario criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "O  Usuário não pode ser criado.")})
    ResponseEntity<Void> createUser(@Valid @RequestBody CreateUserCommand cmd);


    @Operation(description = "Alterar um  usuário", method = "PUT")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Usuario alterado com sucesso."),
            @ApiResponse(responseCode = "400", description = "O  Usuário não pode ser alterado.")})
    ResponseEntity<Void> updateUser(@Valid @PathVariable UUID id, @RequestBody UpdateUserCommand cmd);

    @Operation(description = "Desativar um usuário", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Usuario desativado com sucesso."),
            @ApiResponse(responseCode = "400", description = "O  Usuário não pode ser desativado.")})
    ResponseEntity<Void> deactivateUser(@PathVariable UUID id);

    @Operation(description = "Ativar um usuário", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Usuario Ativado com sucesso."),
            @ApiResponse(responseCode = "400", description = "O  Usuário não pode ser ativado.")})
    ResponseEntity<Void> activateUser(@PathVariable UUID id);

    @Operation(description = "Retorna uma lista de usuarios.", method = "GET")
    Slice<UserReponse> getAll(@ParameterObject GetAlUserRequest filterRequest, Pageable pageable);
}
