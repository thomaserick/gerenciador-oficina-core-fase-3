package com.fiap.pj.core.usuario.adapter.in.api;

import com.fiap.pj.core.sk.web.ResponseEntityUtils;
import com.fiap.pj.core.usuario.adapter.in.api.openapi.UserControllerOpenApi;
import com.fiap.pj.core.usuario.usecase.ActivateUserUseCase;
import com.fiap.pj.core.usuario.usecase.CreateUserUseCase;
import com.fiap.pj.core.usuario.usecase.DisableUserUseCase;
import com.fiap.pj.core.usuario.usecase.UpdateUserUseCase;
import com.fiap.pj.core.usuario.usecase.command.ActivateUserCommand;
import com.fiap.pj.core.usuario.usecase.command.CreateUserCommand;
import com.fiap.pj.core.usuario.usecase.command.DisableUserCommand;
import com.fiap.pj.core.usuario.usecase.command.UpdateUserCommand;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    public static final String PATH = "users";

    private CreateUserUseCase createUserUseCase;
    private DisableUserUseCase disableUserUseCase;
    private ActivateUserUseCase activateUserUseCase;
    private UpdateUserUseCase updateUserUseCase;

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

    @PostMapping("{id}/disable")
    public ResponseEntity<Void> disableUser(@PathVariable UUID id) {
        disableUserUseCase.handle(new DisableUserCommand(id));
        return ResponseEntity.ok().build();
    }


    @PostMapping("{id}/activate")
    public ResponseEntity<Void> activateUser(@PathVariable UUID id) {
        activateUserUseCase.handle(new ActivateUserCommand(id));
        return ResponseEntity.ok().build();
    }


}
