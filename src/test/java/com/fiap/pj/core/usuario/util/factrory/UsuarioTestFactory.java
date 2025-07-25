package com.fiap.pj.core.usuario.util.factrory;


import com.fiap.pj.core.usuario.domain.Usuario;
import com.fiap.pj.core.usuario.domain.enums.PerfilUsuario;
import com.fiap.pj.core.usuario.usecase.command.CriarUsuarioCommand;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class UsuarioTestFactory {

    public static final UUID ID = UUID.randomUUID();
    public static final String NOME = "Hakuna";
    public static final String SOBRE_NOME = "Matata";
    public static final String E_MAIL = "hakuna.matata@gmail.com";
    public static final String SENHA = "1234";
    public static final PerfilUsuario PERFIL_USUARIO = PerfilUsuario.ADM;

    public static Usuario umUsuario() {
        var usuario = new Usuario(ID, NOME, SOBRE_NOME, E_MAIL, SENHA, true);
        usuario.addPerfil(List.of(PERFIL_USUARIO));
        return usuario;
    }

    public static CriarUsuarioCommand umCriarUsuarioCommand() {
        return new CriarUsuarioCommand(NOME, SOBRE_NOME, E_MAIL, SENHA, true, Set.of(PERFIL_USUARIO));
    }
}
