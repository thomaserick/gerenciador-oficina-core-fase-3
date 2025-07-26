package com.fiap.pj.core.usuario.util.factrory;


import com.fiap.pj.core.usuario.domain.User;
import com.fiap.pj.core.usuario.domain.enums.Roles;
import com.fiap.pj.core.usuario.usecase.command.CreateUserCommand;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class UserTestFactory {

    public static final UUID ID = UUID.randomUUID();
    public static final String NOME = "Hakuna";
    public static final String SOBRE_NOME = "Matata";
    public static final String E_MAIL = "hakuna.matata@gmail.com";
    public static final String SENHA = "1234";
    public static final Roles PERFIL_USUARIO = Roles.ADM;

    public static User umUsuario() {
        var usuario = new User(ID, NOME, SOBRE_NOME, E_MAIL, SENHA, true);
        usuario.addRoles(List.of(PERFIL_USUARIO));
        return usuario;
    }

    public static CreateUserCommand umCriarUsuarioCommand() {
        return new CreateUserCommand(NOME, SOBRE_NOME, E_MAIL, SENHA, true, Set.of(PERFIL_USUARIO));
    }
}
