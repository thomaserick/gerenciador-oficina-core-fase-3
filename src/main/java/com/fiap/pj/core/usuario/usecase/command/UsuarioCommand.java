package com.fiap.pj.core.usuario.usecase.command;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiap.pj.core.usuario.domain.enums.PerfilUsuario;

import java.util.Set;
import java.util.UUID;

public record UsuarioCommand(@JsonIgnore UUID id, String nome, String sobreNome, String email,
                             String senha, boolean ativo,
                             Set<PerfilUsuario> perfis) {
}




