package com.fiap.pj.core.user.util.factrory;


import com.fiap.pj.core.user.domain.User;
import com.fiap.pj.core.user.domain.enums.Roles;
import com.fiap.pj.core.user.usecase.command.CreateUserCommand;
import com.fiap.pj.core.user.usecase.command.UpdateUserCommand;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class UserTestFactory {

    public static final UUID ID = UUID.randomUUID();
    public static final String NAME = "Hakuna";
    public static final String LAST_NAME = "Matata";
    public static final String E_MAIL = "hakuna.matata@gmail.com";
    public static final String PASSWORD = "1234";
    public static final Roles USER_ROLE = Roles.ADM;

    public static final String ALTER_NAME = "Jack";
    public static final String ALTER_LAST_NAME = "Daniels";
    public static final String ALTER_E_MAIL = "jack.daniels@gmail.com";
    public static final String ALTER_PASSWORD = "4321";
    public static final Roles ALTER_USER_ROLE = Roles.ATENDENTE;

    public static User oneUser() {
        var usuario = new User(ID, NAME, LAST_NAME, E_MAIL, PASSWORD, true);
        usuario.addRoles(List.of(USER_ROLE));
        return usuario;
    }

    public static CreateUserCommand oneCreateUserCommand() {
        return new CreateUserCommand(NAME, LAST_NAME, E_MAIL, PASSWORD, true, Set.of(USER_ROLE));
    }

    public static UpdateUserCommand umUpdateUserCommand(UUID id) {
        return new UpdateUserCommand(id, ALTER_NAME, ALTER_LAST_NAME, ALTER_E_MAIL, ALTER_PASSWORD, true, Set.of(ALTER_USER_ROLE));
    }
}
