package com.fiap.pj.core.usuario.usecase.command;


import com.fiap.pj.core.usuario.domain.enums.Roles;
import lombok.Getter;

import java.util.Set;


@Getter
public class CreateUserCommand extends UserCommand {

    public CreateUserCommand(String name, String lastName, String email, String password, boolean active, Set<Roles> roles) {
        super(name, lastName, email, password, active, roles);
    }
}




