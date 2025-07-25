package com.fiap.pj.core.usuario.usecase.command;


import com.fiap.pj.core.usuario.domain.enums.PerfilUsuario;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.Set;

import static java.util.Objects.requireNonNull;

@Getter
public abstract class UsuarioCommand {

    @NotBlank(message = "Nome do usuário não pode estar vazio")
    protected String nome;

    protected String sobreNome;

    @NotBlank(message = "E-mail do usuário não pode estar vazio")
    protected String email;

    @NotBlank(message = "Senha do usuário não pode estar vazio")
    protected String senha;

    protected boolean ativo;

    protected Set<PerfilUsuario> perfis;

    protected UsuarioCommand(String nome, String sobreNome, String email, String senha, boolean ativo, Set<PerfilUsuario> perfis) {
        this.nome = requireNonNull(nome);
        this.sobreNome = sobreNome;
        this.email = requireNonNull(email);
        this.senha = requireNonNull(senha);
        this.ativo = ativo;
        this.perfis = requireNonNull(perfis);
    }
}




