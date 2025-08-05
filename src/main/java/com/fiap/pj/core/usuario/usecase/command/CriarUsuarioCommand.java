package com.fiap.pj.core.usuario.usecase.command;


import com.fiap.pj.core.usuario.domain.enums.Perfil;
import lombok.Getter;

import java.util.Set;


@Getter
public class CriarUsuarioCommand extends UserCommand {

    public CriarUsuarioCommand(String nome, String sobreNome, String email, String senha, boolean active, Set<Perfil> perfis) {
        super(nome, sobreNome, email, senha, active, perfis);
    }
}





