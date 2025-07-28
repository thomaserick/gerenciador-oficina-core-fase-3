package com.fiap.pj.core.usuario.usecase.command;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiap.pj.core.usuario.domain.enums.Roles;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;


@Getter
public class UpdateUserCommand extends UserCommand {

    @Setter
    @JsonIgnore
    private UUID id;

    public UpdateUserCommand(UUID id, String name, String lastName, String email, String password, boolean active, Set<Roles> roles) {
        super(name, lastName, email, password, active, roles);
        this.id = id;
    }
}




