package com.fiap.pj.core.usuario.usecase.command;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiap.pj.core.usuario.domain.enums.PerfilUsuario;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;
import java.util.UUID;

public record UsuarioCommand(@JsonIgnore UUID id,
                             @NotBlank(message = "Nome do usuário não pode estar vazio")
                             String nome,
                             String sobreNome,
                             @NotBlank(message = "E-mail do usuário não pode estar vazio")
                             String email,
                             @NotBlank(message = "Senha do usuário não pode estar vazio")
                             String senha,
                             boolean ativo,
                             Set<PerfilUsuario> perfis) {
}




