package com.fiap.pj.core.usuario.usecase.command;


public record UsuarioCommand(
        Long id,
        String nome,
        String email,
        String senha) {
}




