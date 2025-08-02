package com.fiap.pj.core.usuario.usecase.command;


import com.fiap.pj.core.usuario.domain.enums.Perfil;
import lombok.Getter;

import java.util.Set;


@Getter
public class CriarUsuarioCommand extends UserCommand {

    public CriarUsuarioCommand(String name, String lastName, String email, String password, boolean active, Set<Perfil> roles) {
        super(name, lastName, email, password, active, roles);
    }
}




