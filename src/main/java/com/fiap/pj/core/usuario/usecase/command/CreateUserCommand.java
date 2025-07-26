package com.fiap.pj.core.usuario.usecase.command;


import com.fiap.pj.core.usuario.domain.enums.Roles;
import lombok.Getter;

import java.util.Set;


@Getter
public class CreateUserCommand extends UserCommand {

    public CreateUserCommand(String nome, String sobreNome, String email, String senha, boolean ativo, Set<Roles> perfis) {
        super(nome, sobreNome, email, senha, ativo, perfis);
    }
}




