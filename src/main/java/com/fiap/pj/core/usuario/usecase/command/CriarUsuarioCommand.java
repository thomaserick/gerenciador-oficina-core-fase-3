package com.fiap.pj.core.usuario.usecase.command;


import com.fiap.pj.core.usuario.domain.enums.PerfilUsuario;
import lombok.Getter;

import java.util.Set;


@Getter
public class CriarUsuarioCommand extends UsuarioCommand {

    public CriarUsuarioCommand(String nome, String sobreNome, String email, String senha, boolean ativo, Set<PerfilUsuario> perfis) {
        super(nome, sobreNome, email, senha, ativo, perfis);
    }
}




