package com.fiap.pj.core.usuario.adapter.in.api;

import com.fiap.pj.core.sk.web.ResponseEntityUtils;
import com.fiap.pj.core.usuario.adapter.in.api.openapi.UserControllerOpenApi;
import com.fiap.pj.core.usuario.adapter.in.api.request.GetAlUserRequest;
import com.fiap.pj.core.usuario.adapter.in.api.response.UserReponse;
import com.fiap.pj.core.usuario.usecase.ActivateUserUseCase;
import com.fiap.pj.core.usuario.usecase.CreateUserUseCase;
import com.fiap.pj.core.usuario.usecase.DeactivateUserUseCase;
import com.fiap.pj.core.usuario.usecase.GetAllUserUseCase;
import com.fiap.pj.core.usuario.usecase.UpdateUserUseCase;
import com.fiap.pj.core.usuario.usecase.command.ActivateUserCommand;
import com.fiap.pj.core.usuario.usecase.command.CreateUserCommand;
import com.fiap.pj.core.usuario.usecase.command.DeactivateUserCommand;
import com.fiap.pj.core.usuario.usecase.command.UpdateUserCommand;
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
@RequestMapping(path = UserController.PATH)
@AllArgsConstructor
public class UserController implements UserControllerOpenApi {

    public static final String PATH = "v1/users";

    private CreateUserUseCase createUserUseCase;
    private DeactivateUserUseCase deactivateUserUseCase;
    private ActivateUserUseCase activateUserUseCase;
    private UpdateUserUseCase updateUserUseCase;
    private GetAllUserUseCase getAllUserUseCase;

    @PostMapping
    public ResponseEntity<Void> createUser(@Valid @RequestBody CreateUserCommand cmd) {
        var usuario = createUserUseCase.handle(cmd);
        return ResponseEntityUtils.create(getClass(), usuario.getId());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@Valid @PathVariable UUID id, @RequestBody UpdateUserCommand cmd) {
        cmd.setId(id);
        updateUserUseCase.handle(cmd);
        return ResponseEntity.ok().build();
    }

    @PostMapping("{id}/deactivate")
    public ResponseEntity<Void> deactivateUser(@PathVariable UUID id) {
        deactivateUserUseCase.handle(new DeactivateUserCommand(id));
        return ResponseEntity.ok().build();
    }


    @PostMapping("{id}/activate")
    public ResponseEntity<Void> activateUser(@PathVariable UUID id) {
        activateUserUseCase.handle(new ActivateUserCommand(id));
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping
    public Slice<UserReponse> getAll(@ParameterObject GetAlUserRequest filterRequest, @ParameterObject Pageable pageable) {
        filterRequest.setPageable(pageable);
        return getAllUserUseCase.handle(filterRequest);
    }


}
